package fr.epikdino.producer;

import org.bukkit.event.Listener;

import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public abstract class EventProducer extends Producer implements Listener{

    public EventProducer(ConsumerPool consumers, ListenerConfig listener) {
        super(consumers, listener);
    }
    
}
