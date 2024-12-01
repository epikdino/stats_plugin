package fr.epikdino.consumer;

import org.bukkit.plugin.Plugin;

public abstract class RealtimeConsumer extends Consumer {

    protected RealtimeConsumer(String name, Plugin plugin) {
        super(-1, 1, name, plugin);
    }

}
