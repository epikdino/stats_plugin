package fr.epikdino.statsgenerator.producer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.Stat;
import fr.epikdino.statsgenerator.consumer.ConsumerPool;
import fr.epikdino.statsgenerator.config.ConfigManager.Config.ListenerConfig;

public class PlayerWorldProducer extends TimedProducer{

    public PlayerWorldProducer(ConsumerPool consumers, ListenerConfig listener, Plugin plugin) {
        super(consumers, listener, plugin);
    }

    @Override
    protected List<Stat> getStatNow() {
        Map<String, Long> worlds = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            String worldName = player.getWorld().getName();
            worlds.put(worldName, worlds.getOrDefault(worldName, 0L) + 1);
        }
        this.value = "";
        for (Map.Entry<String, Long> entry : worlds.entrySet()) {
            this.value += entry.getKey() + ":" + entry.getValue().toString() + ";";
        }
        return super.getStatNow();
    }
    
}
