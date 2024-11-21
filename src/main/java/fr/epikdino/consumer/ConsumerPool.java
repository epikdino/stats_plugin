package fr.epikdino.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.epikdino.Stat;
import fr.epikdino.config.ConfigManager.Config.StorageConfig;

public class ConsumerPool {

    private List<Consumer> consumers;

    public ConsumerPool(List<StorageConfig> storages) {
        ConsumerFactory factory = new ConsumerFactory();
        consumers = new ArrayList<>();
        for (StorageConfig storage : storages) {
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
            try{
                consumer.addStat(stat);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
