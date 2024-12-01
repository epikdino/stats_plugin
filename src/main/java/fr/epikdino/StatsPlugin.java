package fr.epikdino;

import org.bukkit.plugin.java.JavaPlugin;

import fr.epikdino.config.ConfigManager;
import fr.epikdino.consumer.ConsumerPool;
import fr.epikdino.producer.ProducerPool;

public class StatsPlugin extends JavaPlugin {

    public ConfigManager cfg;
    private ProducerPool producerPool;
    private ConsumerPool consumerPool;

    @Override
    public void onEnable() {
        getLogger().info("Minecraft plugin enabled!");
        reloadConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Minecraft plugin disabled!");
        producerPool.close();
        producerPool = null;
        consumerPool.close();
        consumerPool = null;
    }

    public void reloadConfig() {
        cfg = ConfigManager.getInstance(this);
        cfg.reload();
        producerPool = null;
        consumerPool = null;
        consumerPool = new ConsumerPool(cfg.getConfig().storages, this);
        producerPool = new ProducerPool(cfg.getConfig().listeners, consumerPool, this);
    }
}
