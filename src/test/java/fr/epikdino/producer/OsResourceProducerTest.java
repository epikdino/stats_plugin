package fr.epikdino.producer;

import fr.epikdino.Stat;
import fr.epikdino.StatsPlugin;
import fr.epikdino.config.ConfigManager;
import fr.epikdino.consumer.ConsumerPool;

import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OSResourceProducerTest {

    private OSResourceProducer producer;
    private ConsumerPool consumerPool;
    private ConfigManager configManager;
    private Plugin plugin;
    private Path tempConfigFile;
    
    @BeforeEach
    void setUp() throws IOException{
        consumerPool = mock(ConsumerPool.class);
        MockBukkit.mock();
        plugin = MockBukkit.load(StatsPlugin.class);

        File dataFolder = plugin.getDataFolder();
        dataFolder.mkdirs();
        tempConfigFile = dataFolder.toPath().resolve("config.yaml");

        // Copy the contents of base-config.yaml to the temporary file
        Path baseConfigPath = Path.of("src/test/resources/config-osresources.yaml");
        Files.copy(baseConfigPath, tempConfigFile, StandardCopyOption.REPLACE_EXISTING);

        configManager = ConfigManager.getInstance(plugin);
        configManager.reload();

        producer = new OSResourceProducer(consumerPool, configManager.getConfig().listeners.get(0), plugin);
    }

    @AfterEach
    void tearDown() {
        producer = null;
        MockBukkit.unmock();
    }

    @Test
    void testRetrieveStats() {
        List<Stat> stats = producer.getStatNow();
        assertNotNull(stats);
        assertFalse(stats.isEmpty());
    }


    // Add more tests for other methods if needed
}