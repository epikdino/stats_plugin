package fr.epikdino.producer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import fr.epikdino.Stat;
import fr.epikdino.consumer.ConsumerPool;

public abstract class TimedProducer extends Producer {
    private JavaPlugin plugin;
    private BukkitTask task;

    public TimedProducer(ConsumerPool consumerPool, long interval) {
        super(consumerPool);
        this.plugin = JavaPlugin.getProvidingPlugin(getClass());
        task = Bukkit.getScheduler().runTaskTimer(plugin, this::collect, 0L, interval);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
        }
    }

    private void collect() {
        for(Stat stat : retrieveStats()){
            consumers.consume(stat);
        }
    }

    protected List<Stat> retrieveStats() {
        return new ArrayList<>();
    }

    protected Stat retrieveStat() {
        throw new UnsupportedOperationException("Not implemented here");
    }

    @Override
    public void close() {
        stop();
    }
}