package fr.epikdino;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Minecraft plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Minecraft plugin disabled!");
    }
}