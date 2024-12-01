package fr.epikdino.producer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public class PlayerDeathProducer extends EventProducer {

    public PlayerDeathProducer(ConsumerPool consumers, ListenerConfig listener, Plugin plugin) {
        super(consumers, listener, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        this.value = String.format("player:%s;killer:%s;level:%s;world:%s;reason:%s",
                                event.getEntity().getName(),
                                event.getEntity().getKiller() != null ? event.getEntity().getKiller().getName() : "Unknown",
                                event.getEntity().getLevel(),
                                event.getEntity().getWorld().getName(),
                                event.getEntity().getLastDamageCause().getCause().toString());

        sendStat(getStatNow());
    }
    
}
