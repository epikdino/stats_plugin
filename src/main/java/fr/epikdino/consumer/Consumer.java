package fr.epikdino.consumer;

import fr.epikdino.Stat;

public abstract class Consumer {

    public abstract void consume(Stat stat);

    public abstract void close();

}
