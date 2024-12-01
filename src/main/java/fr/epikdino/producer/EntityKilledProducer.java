package fr.epikdino.producer;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public class EntityKilledProducer extends EventProducer{
    
    public EntityKilledProducer(ConsumerPool consumers, ListenerConfig listener, Plugin plugin) {
        super(consumers, listener, plugin);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            return; // Ignore player deaths
        }

        this.value = String.format("entity:%s;world:%s;count:%s;killer:%s",
                        event.getEntity().getType().name(),
                        event.getEntity().getWorld().getName(),
                        1,
                        event.getEntity().getKiller() != null ? event.getEntity().getKiller().getName() : "Unknown");
        sendStat(getStatNow());
    }
}
