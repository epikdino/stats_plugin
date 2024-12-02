package fr.epikdino.statsgenerator.consumer;

import org.bukkit.plugin.Plugin;

import fr.epikdino.statsgenerator.Stat;
import fr.epikdino.statsgenerator.config.ConfigManager.Config.StorageConfig;
import fr.epikdino.statsgenerator.tools.websocket.WebSocketServerHandler;

public class WebSocketConsumer extends RealtimeConsumer {

    private WebSocketServerHandler server;

    public WebSocketConsumer(StorageConfig config, Plugin plugin) {
        super(config.name, plugin);
        logger.info("Initializing wbsocket consumer on port " + config.port);
        this.server = new WebSocketServerHandler(config.port);
    }

    @Override
    public void consume() {
        while (!queue.isEmpty()) {
            Stat stat = queue.poll();
            this.server.sendData(stat.getJsonValue());
        }
    }

    @Override
    public void close() {
        logger.info("Closing websocket consumer");
        this.server.stop();
        super.close();
    }

    @Override
    public void startConsumer() {
    }
}