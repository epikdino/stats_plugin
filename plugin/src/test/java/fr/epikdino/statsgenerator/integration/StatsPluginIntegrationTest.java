package fr.epikdino.statsgenerator.integration;

import org.mockbukkit.mockbukkit.MockBukkit;

import fr.epikdino.statsgenerator.StatsGeneratorPlugin;

import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatsPluginIntegrationTest {

    private Plugin plugin;

    @BeforeEach
    void setUp() {
        MockBukkit.mock();
        plugin = MockBukkit.load(StatsGeneratorPlugin.class);
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testPluginEnable() {
        assertTrue(plugin.isEnabled());
    }

    @Test
    void testPluginDisable() {
        MockBukkit.unmock();
        assertFalse(plugin.isEnabled());
    }
}