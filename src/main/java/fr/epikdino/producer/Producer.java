package fr.epikdino.producer;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import fr.epikdino.Main;
import fr.epikdino.Stat;
import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public abstract class Producer {

    protected ConsumerPool consumers;
    protected String name;

    public Producer(ConsumerPool consumers, ListenerConfig listener) {
        this.consumers = consumers;
        this.name = listener.name;
    }

    protected void sendStat(Stat stat) {
        consumers.consume(stat);
    }

    protected void sendStat(List<Stat> stats){
        for(Stat stat : stats){
            sendStat(stat);
        }
    }

    protected void sendStat(){
        sendStat(getStatNow());
    }

    protected abstract List<Stat> getStatNow();

    public void close(){
        JavaPlugin.getPlugin(Main.class).getLogger().info("Closing producer " + this.name);
    }

}
