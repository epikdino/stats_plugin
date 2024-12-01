package fr.epikdino.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.bukkit.plugin.Plugin;

public class ConfigManager {
    private static ConfigManager instance;
    private static final String CONFIG_FILE_NAME = "config.yaml";
    private Config config;
    private Plugin plugin;

    private ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        reload();
    }

    public static ConfigManager getInstance(Plugin plugin) {
        if (instance == null) {
            instance = new ConfigManager(plugin);
        }
        return instance;
    }

    public static ConfigManager getInstance(){
        return instance;
    }

    public Config getConfig() {
        return config;
    }

    public void reload() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            InputStream inputStream = getConfigStream();
            config = mapper.readValue(inputStream, Config.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    private InputStream getConfigStream() throws IOException {
        InputStream defaultConfig = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
        //File dataFolder = JavaPlugin.getPlugin(StatsPlugin.class).getDataFolder();
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        if (dataFolder.getAbsolutePath() == null) return defaultConfig;
        Path configPath = Paths.get(dataFolder.getAbsolutePath(), CONFIG_FILE_NAME);
        if (!Files.exists(configPath)) return defaultConfig;
        return Files.newInputStream(configPath);
    }

    public static class Config {
        public List<StorageConfig> storages;
        public List<ListenerConfig> listeners;

        public static class StorageConfig {
            public String type; // file, database, websocket
            public String name;
            public String filepath;
            public long interval; // in seconds
            public long max_size; // in elements
            public int  port; // for websocket or database
        }

        public static class ListenerConfig {
            public String type; // timed, event
            public String name;
            public int interval; // in seconds
            public String source;
            public String filter;
            public String capture;
        }
    }
}