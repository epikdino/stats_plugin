package fr.epikdino.statsgenerator.producer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.consumer.ConsumerPool;
import fr.epikdino.statsgenerator.logger.PluginLogger;
import fr.epikdino.statsgenerator.config.ConfigManager.Config.ListenerConfig;

public class ProducerPool {

    private List<Producer> producers;
    private PluginLogger logger;

    public ProducerPool(List<ListenerConfig> listeners, ConsumerPool consumers, Plugin plugin) {
        this.logger = new PluginLogger(plugin, "ProducerPool");
        ProducerFactory factory = new ProducerFactory();
        producers = new ArrayList<>();
        for (ListenerConfig listener : listeners) {
            producers.add(factory.createProducer(listener, consumers, plugin));
        }
        logger.info("Inittialized producer pool.");
    }

    public void close() {
        logger.info("Closing all producers.");
        for (Producer producer : producers) {
            producer.close();
        }
    }

}
