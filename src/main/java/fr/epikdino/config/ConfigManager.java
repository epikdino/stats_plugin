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
        public List<Storage> storages;
        public List<Listener> listeners;

        public static class Storage {
            public String type; // file, database, websocket
            public FileConfig file;
            public DatabaseConfig database;
            public WebSocketConfig websocket;

            public static class FileConfig {
                public String path;
            }

            public static class DatabaseConfig {
                public String url;
                public String username;
                public String password;
            }

            public static class WebSocketConfig {
                public boolean enabled;
                public int port;
            }
        }

        public static class Listener {
            public String type; // timed, event
            public String name;
            public int interval; // in seconds
        }
    }
}