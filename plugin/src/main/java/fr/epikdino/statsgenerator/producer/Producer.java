package fr.epikdino.statsgenerator.producer;

import java.util.Collections;
import java.util.List;

import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.Stat;
import fr.epikdino.statsgenerator.consumer.ConsumerPool;
import fr.epikdino.statsgenerator.logger.PluginLogger;
import fr.epikdino.statsgenerator.config.ConfigManager.Config.ListenerConfig;

public abstract class Producer {

    protected ConsumerPool consumers;
    protected String name;
    protected String value;
    protected Plugin plugin;
    protected PluginLogger logger;

    public Producer(ConsumerPool consumers, ListenerConfig listener, Plugin plugin) {
        this.consumers = consumers;
        this.name = listener.name;
        this.plugin = plugin;
        this.logger = new PluginLogger(plugin, name);
        logger.info("Creating producer " + this.name);
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

    protected List<Stat> getStatNow(){
        return Collections.singletonList(new Stat(name, value));
    }

    public void close(){
        logger.info("Closing producer " + this.name);
    }

}
