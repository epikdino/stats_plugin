package fr.epikdino.statsgenerator.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.Stat;
import fr.epikdino.statsgenerator.logger.PluginLogger;

public abstract class Consumer implements Runnable {

    protected BlockingQueue<Stat> queue;
    protected long interval;
    protected long maxSize;
    protected Plugin plugin;
    protected PluginLogger logger;
    protected String name;
    private Thread thread;
    private ScheduledExecutorService scheduler;

    protected Consumer(long interval, long maxSize, String name, Plugin plugin) {
        this.plugin = plugin;
        this.name = name;
        this.logger = new PluginLogger(plugin, name);
        this.interval = interval;
        this.maxSize = maxSize;
        this.queue = new LinkedBlockingQueue<>((int) maxSize);
        this.thread = new Thread(this);
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.thread.start();
        logger.info("Started consumer " + this.name);
    }

    public abstract void consume();

    public abstract void startConsumer();

    public void close(){
        logger.info("Closing consumer " + this);
        thread.interrupt();
    }

    public void addStat(Stat stat) throws InterruptedException{
        queue.put(stat);
    }

    @Override
    public  void run(){
        startConsumer();
        startScheduler();
        while (!Thread.currentThread().isInterrupted()) {
            while (!Thread.currentThread().isInterrupted()) {
                if (queue.size() < maxSize) continue;
                flushQueue();
            }
        }
    }

    private void startScheduler() {
        if (interval <= 0) return;
        scheduler.scheduleAtFixedRate(this::flushQueue, interval * 50, interval * 50, TimeUnit.MILLISECONDS);
    }

    private void flushQueue() {
        if(queue.isEmpty()) return;
        consume();;
        queue.clear();
    }

}
