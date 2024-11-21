package fr.epikdino.consumer;

import fr.epikdino.Stat;
import fr.epikdino.tools.websocket.WebSocketServerHandler;

public class WebSocketConsumer extends RealtimeConsumer {

    private WebSocketServerHandler server;

    public WebSocketConsumer(int port) {
        this.server = new WebSocketServerHandler(port);
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
        server.stop();
        super.close();
    }

    @Override
    public void startConsumer() {
    }
}