package fr.epikdino.statsgenerator.consumer;

import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.Stat;
import fr.epikdino.statsgenerator.config.ConfigManager.Config.StorageConfig;

public class CSVConsumer extends BatchConsumer{

    private String filepath;

    public CSVConsumer(StorageConfig config, Plugin plugin) {
        super(config, plugin);
        this.filepath = config.filepath;
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
