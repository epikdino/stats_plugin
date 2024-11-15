package fr.epikdino.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.epikdino.Stat;
import fr.epikdino.config.ConfigManager.Config.Storage;

public class ConsumerPool {

    private List<Consumer> consumers;

    public ConsumerPool(List<Storage> storages) {
        ConsumerFactory factory = new ConsumerFactory();
        consumers = new ArrayList<>();
        for (Storage storage : storages) {
            consumers.add(factory.createConsumer(storage));
        }
    }

    public void finalize() {
        for (Consumer consumer : consumers) {
            consumer.close();
        }
    }

    public void dump(Logger logger) {
        logger.info("Initialized consumer pool.");
    }

    public void consume(Stat stat) {
        for(Consumer consumer : consumers) {
            consumer.consume(stat);
        }
    }

}
