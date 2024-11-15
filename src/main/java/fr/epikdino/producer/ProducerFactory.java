package fr.epikdino.producer;

import fr.epikdino.config.ConfigManager.Config.Listener;
import fr.epikdino.consumer.ConsumerPool;

public class ProducerFactory {

    public Producer createProducer(Listener listener, ConsumerPool consumers) {
        switch(listener.name){
            case "OSResources":
                return new OSResourceProducer(consumers, listener.interval);
            default:
                throw new UnsupportedOperationException("No listener found with that name");
        }
    }

}
