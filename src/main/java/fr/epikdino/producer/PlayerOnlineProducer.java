package fr.epikdino.producer;

import java.util.Collections;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import fr.epikdino.Stat;
import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public class PlayerOnlineProducer extends EventProducer{

    private long onlinePlayers;
    
    public PlayerOnlineProducer(ConsumerPool consumers, ListenerConfig listener, Plugin plugin) {
        super(consumers, listener, plugin);
        onlinePlayers = 0;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ++onlinePlayers;
        sendStat(getStatNow());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        --onlinePlayers;
        sendStat(getStatNow());
    }

    @Override
    public List<Stat> getStatNow(){
        return Collections.singletonList(new Stat(this.name, onlinePlayers));
    }
}
