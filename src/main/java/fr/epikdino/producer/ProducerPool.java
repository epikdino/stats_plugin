package fr.epikdino.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.epikdino.config.ConfigManager.Config.Listener;
import fr.epikdino.consumer.ConsumerPool;

public class ProducerPool {

    List<Producer> producers;

    public ProducerPool(List<Listener> listeners, ConsumerPool consumers) {
        ProducerFactory factory = new ProducerFactory();
        producers = new ArrayList<>();
        for (Listener listener : listeners) {
            producers.add(factory.createProducer(listener, consumers));
        }
    }

    public void dump(Logger logger) {
        logger.info("Inittialized producer pool.");
    }

    public void finalize() {
        for (Producer producer : producers) {
            producer.close();
        }
    }

}
