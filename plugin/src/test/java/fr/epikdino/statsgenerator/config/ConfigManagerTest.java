package fr.epikdino.statsgenerator.config;

import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockbukkit.mockbukkit.MockBukkit;
import fr.epikdino.statsgenerator.StatsGeneratorPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

class ConfigManagerTest {

    private Plugin plugin;
    private Path tempConfigFile;

    @BeforeEach
    void setUp() throws IOException {
        MockBukkit.mock();
        plugin = MockBukkit.load(StatsGeneratorPlugin.class);
        // Create a temporary configuration file in the plugin's data folder
        File dataFolder = plugin.getDataFolder();
        dataFolder.mkdirs();
        tempConfigFile = dataFolder.toPath().resolve("config.yaml");

        // Copy the contents of base-config.yaml to the temporary file
        Path baseConfigPath = Path.of("src/test/resources/base-config.yaml");
        Files.copy(baseConfigPath, tempConfigFile, StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Unload the plugin
        MockBukkit.unmock();
        Files.deleteIfExists(tempConfigFile);
        Files.deleteIfExists(tempConfigFile.getParent());
    }

    @Test
    void testLoadConfigFromSpecificFolder() {
        // Load the configuration using ConfigManager
        ConfigManager configManager = ConfigManager.getInstance();
        configManager.reload();

        // Verify the configuration
        ConfigManager.Config config = configManager.getConfig();
        assertNotNull(config);
        assertFalse(config.listeners.isEmpty());
        assertFalse(config.storages.isEmpty());
    }

    @Test
    void testLoadConfigFromClasspath() throws IOException {
        // Delete the temporary configuration file to force loading from classpath
        Files.deleteIfExists(tempConfigFile);

        // Load the configuration using ConfigManager
        ConfigManager configManager = ConfigManager.getInstance();
        configManager.reload();

        // Verify the configuration
        ConfigManager.Config config = configManager.getConfig();
        assertNotNull(config);
        assertFalse(config.listeners.isEmpty());
        assertFalse(config.storages.isEmpty());
    }

    // Add more tests for other methods if needed
}