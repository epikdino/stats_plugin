package fr.epikdino.producer;

import fr.epikdino.consumer.ConsumerPool;

public abstract class EventProducer extends Producer {

    public EventProducer(ConsumerPool consumers) {
        super(consumers);
    }

    public abstract void produce();

    public abstract void close();
    
}
