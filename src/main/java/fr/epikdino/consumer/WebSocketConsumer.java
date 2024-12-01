package fr.epikdino.consumer;

import org.bukkit.plugin.Plugin;

import fr.epikdino.Stat;
import fr.epikdino.config.ConfigManager.Config.StorageConfig;
import fr.epikdino.tools.websocket.WebSocketServerHandler;

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