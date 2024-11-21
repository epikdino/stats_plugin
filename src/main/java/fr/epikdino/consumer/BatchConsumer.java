package fr.epikdino.consumer;

public abstract class BatchConsumer extends Consumer {

    public BatchConsumer(long interval, long max_size) {
        super(interval, max_size);
    }
    
}
