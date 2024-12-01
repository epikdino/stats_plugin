package fr.epikdino.producer;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public abstract class EventProducer extends Producer implements Listener{

    public EventProducer(ConsumerPool consumers, ListenerConfig listener, Plugin plugin) {
        super(consumers, listener, plugin);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    
}
