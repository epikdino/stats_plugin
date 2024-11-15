package fr.epikdino.consumer;

import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import fr.epikdino.Stat;

public abstract class BatchConsumer extends Consumer {

    protected Queue<Stat> queue;
    protected long interval;
    protected long max_size;
    private JavaPlugin plugin;
    private BukkitTask task;

    public BatchConsumer(long interval, long max_size) {
        super();
        queue = new LinkedList<>();
        this.max_size = max_size;
        this.interval = interval;
        startTask();
    }

    private void startTask(){
        this.plugin = JavaPlugin.getProvidingPlugin(getClass());
        task = Bukkit.getScheduler().runTaskTimer(plugin, this::consumeBatch, interval, interval);
    }

    public abstract void consumeBatch();

    @Override
    public void consume(Stat stat) {
        queue.add(stat);
        if (queue.size() >= max_size){
            task.cancel();
            consumeBatch();
            startTask();
        }
    }
    
}
