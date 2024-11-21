package fr.epikdino.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fr.epikdino.Stat;

public abstract class Consumer implements Runnable {

    protected BlockingQueue<Stat> queue;
    protected long interval;
    protected long maxSize;
    private Thread thread;
    private ScheduledExecutorService scheduler;

    protected Consumer(long interval, long maxSize) {
        this.interval = interval;
        this.maxSize = maxSize;
        this.queue = new LinkedBlockingQueue<>((int) maxSize);
        this.thread = new Thread(this);
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.thread.start();
    }

    public abstract void consume();

    public abstract void startConsumer();

    public void close(){
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
