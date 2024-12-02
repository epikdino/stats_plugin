package fr.epikdino.statsgenerator.consumer;

import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.config.ConfigManager.Config.StorageConfig;

public class ConsumerFactory {

    public Consumer createConsumer(StorageConfig storage, Plugin plugin) {
        switch(storage.type) {
            case "Csv":
                return new CSVConsumer(storage, plugin);
            case "WebSocket":
                return new WebSocketConsumer(storage, plugin);
            default:
                throw new IllegalArgumentException("Unknown storage type: " + storage.type);
        }
    }

}
