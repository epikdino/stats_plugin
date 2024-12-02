package fr.epikdino.statsgenerator.tools.websocket;

import java.util.concurrent.CopyOnWriteArraySet;

import org.glassfish.tyrus.server.Server;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/")
public class WebSocketServerHandler{

    private static final CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();
    private Server server;

    public WebSocketServerHandler(int port) {
        this.server = new Server("localhost", port, "/ws", null, WebSocketServerHandler.class);
        try {
            this.server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    public void sendData(String data){
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(data);        
        }
    }

    public void stop(){
        if (server == null) return;
        server.stop();
    }
}