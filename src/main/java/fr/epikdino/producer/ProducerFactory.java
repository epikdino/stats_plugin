package fr.epikdino.producer;

import org.bukkit.plugin.Plugin;

import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public class ProducerFactory {

    public Producer createProducer(ListenerConfig listener, ConsumerPool consumers, Plugin plugin){
        switch(listener.type){
            case "Command":
                return new CommandProducer(consumers, listener, plugin);
            case "EntityKilled":
                return new EntityKilledProducer(consumers, listener, plugin);
            case "OSResources":
                return new OSResourceProducer(consumers, listener, plugin);
            case "PlayerDeath":
                return new PlayerDeathProducer(consumers, listener, plugin);
            case "PlayerOnline":
                return new PlayerOnlineProducer(consumers, listener, plugin);
            case "PlayerWorld":
                return new PlayerWorldProducer(consumers, listener, plugin);
            default:
                throw new UnsupportedOperationException("No listener found with that name");
        }
    }

}
