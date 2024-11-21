package fr.epikdino.consumer;

import fr.epikdino.config.ConfigManager.Config.StorageConfig;

public class ConsumerFactory {

    public Consumer createConsumer(StorageConfig storage) {
        switch(storage.type) {
            case "Csv":
                return new CSVConsumer(storage.interval, storage.max_size, storage.filepath);
            case "WebSocket":
                return new WebSocketConsumer(storage.port);
            default:
                throw new IllegalArgumentException("Unknown storage type: " + storage.type);
        }
    }

}
