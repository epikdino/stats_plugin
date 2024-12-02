package fr.epikdino.statsgenerator.consumer;

import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.config.ConfigManager.Config.StorageConfig;

public abstract class BatchConsumer extends Consumer {

    public BatchConsumer(StorageConfig config, Plugin plugin) {
        super(config.interval, config.max_size, config.name, plugin);
    }
    
}
