package fr.epikdino.producer;

import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public class ProducerFactory {

    public Producer createProducer(ListenerConfig listener, ConsumerPool consumers) {
        switch(listener.name){
            case "Command":
                return new CommandProducer(consumers, listener);
            case "EntityKilled":
                return new EntityKilledProducer(consumers, listener);
            case "OSResources":
                return new OSResourceProducer(consumers, listener);
            case "PlayerDeath":
                return new PlayerDeathProducer(consumers, listener);
            case "PlayerOnline":
                return new PlayerOnlineProducer(consumers, listener);
            case "PlayerWorld":
                return new PlayerWorldProducer(consumers, listener);
            default:
                throw new UnsupportedOperationException("No listener found with that name");
        }
    }

}
