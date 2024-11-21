package fr.epikdino.producer;

import java.util.List;

import fr.epikdino.Stat;
import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public class PlayerDeathProducer extends EventProducer {

    public PlayerDeathProducer(ConsumerPool consumers, ListenerConfig listener){
        super(consumers, listener);
    }

    @Override
    protected List<Stat> getStatNow() {
        throw new UnsupportedOperationException("Unimplemented method 'getStatNow'");
    }
    
}
