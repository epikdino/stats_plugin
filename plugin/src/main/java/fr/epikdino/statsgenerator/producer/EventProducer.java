package fr.epikdino.statsgenerator.producer;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.consumer.ConsumerPool;
import fr.epikdino.statsgenerator.config.ConfigManager.Config.ListenerConfig;

public abstract class EventProducer extends Producer implements Listener{

    public EventProducer(ConsumerPool consumers, ListenerConfig listener, Plugin plugin) {
        super(consumers, listener, plugin);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    
}
