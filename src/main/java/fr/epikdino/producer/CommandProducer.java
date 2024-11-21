package fr.epikdino.producer;

import java.util.List;

import fr.epikdino.Stat;
import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public class CommandProducer extends EventProducer{
    
    public CommandProducer(ConsumerPool consumers, ListenerConfig listener){
        super(consumers, listener);
        // get a custom name for this stat ? or for all stats
        // add filter to catch only some commands
        // add filter to capture only command from player or from server
        // add capture regex to retrieve arguments from command
    }

    @Override
    protected List<Stat> getStatNow() {
        throw new UnsupportedOperationException("Unimplemented method 'getStatNow'");
    }

}
