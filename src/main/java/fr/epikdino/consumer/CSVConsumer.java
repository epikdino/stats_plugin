package fr.epikdino.consumer;

import java.io.FileWriter;
import java.io.IOException;

import fr.epikdino.Stat;

public class CSVConsumer extends BatchConsumer{

    private String filepath;

    public CSVConsumer(long interval, long max_size, String filepath){
        super(interval, max_size);
        this.filepath = filepath;
    }

    @Override
    public void consume() {
        try (FileWriter fileWriter = new FileWriter(filepath, true)) {
            while (! queue.isEmpty()){
                Stat stat = queue.poll();
                fileWriter.append(stat.tspString() + ";" + stat.name + ";" + stat.value + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startConsumer() {}
    
}
