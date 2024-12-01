package fr.epikdino.producer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import fr.epikdino.Stat;
import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public abstract class TimedProducer extends Producer {
    private BukkitTask task;

    public TimedProducer(ConsumerPool consumerPool, ListenerConfig listener, Plugin plugin) {
        super(consumerPool, listener, plugin);
        task = Bukkit.getScheduler().runTaskTimer(plugin, this::collect, 0L, listener.interval);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
        }
    }

    private void collect() {
        for(Stat stat : getStatNow()){
            consumers.consume(stat);
        }
    }

    @Override
    public void close() {
        stop();
    }
}