package fr.epikdino.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigManager {
    private static ConfigManager instance;
    private Config config;

    private ConfigManager() {
        reload();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public Config getConfig() {
        return config;
    }

    public void reload() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            config = mapper.readValue(new File("config.yaml"), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Config {
        public List<StorageConfig> storages;
        public List<ListenerConfig> listeners;

        public static class StorageConfig {
            public String type; // file, database, websocket
            public String filepath;
            public long interval; // in seconds
            public long max_size; // in elements
            public int  port; // for websocket or database
        }

        public static class ListenerConfig {
            public String type; // timed, event
            public String name;
            public int interval; // in seconds
        }
    }
}