package fr.epikdino.statsgenerator;

import org.bukkit.plugin.java.JavaPlugin;

import fr.epikdino.statsgenerator.api.StatsGenerator;

import fr.epikdino.statsgenerator.consumer.ConsumerPool;
import fr.epikdino.statsgenerator.producer.ProducerPool;
import fr.epikdino.statsgenerator.config.ConfigManager;

public class StatsGeneratorPlugin extends JavaPlugin implements StatsGenerator {

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

    @Override
    public void addStat(String name, String value) {
        consumerPool.consume(new Stat(name, value));
    }
}
