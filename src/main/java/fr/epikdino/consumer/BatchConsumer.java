package fr.epikdino.consumer;

import org.bukkit.plugin.Plugin;

import fr.epikdino.config.ConfigManager.Config.StorageConfig;

public abstract class BatchConsumer extends Consumer {

    public BatchConsumer(StorageConfig config, Plugin plugin) {
        super(config.interval, config.max_size, config.name, plugin);
    }
    
}
