package fr.epikdino.statsgenerator.consumer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.Stat;
import fr.epikdino.statsgenerator.config.ConfigManager.Config.StorageConfig;
import fr.epikdino.statsgenerator.logger.PluginLogger;

public class ConsumerPool implements AutoCloseable{

    private List<Consumer> consumers;
    private PluginLogger logger;

    public ConsumerPool(List<StorageConfig> storages, Plugin plugin) {
        this.logger = new PluginLogger(plugin, "ConsumerPool");
        ConsumerFactory factory = new ConsumerFactory();
        consumers = new ArrayList<>();
        for (StorageConfig storage : storages) {
            consumers.add(factory.createConsumer(storage, plugin));
        }
        logger.info("Initialized consumer pool.");
    }

    @Override
    public void close() {
        logger.info("Closing all consumers.");
        for (Consumer consumer : consumers) {
            consumer.close();
        }
    }

    public void consume(Stat stat) {
        for(Consumer consumer : consumers) {
            try{
                consumer.addStat(stat);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
