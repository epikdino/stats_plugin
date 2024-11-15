package fr.epikdino.producer;

import fr.epikdino.consumer.ConsumerPool;

public abstract class Producer {

    protected ConsumerPool consumers;

    public Producer(ConsumerPool consumers) {
        this.consumers = consumers;
    }

    public abstract void close();

}
