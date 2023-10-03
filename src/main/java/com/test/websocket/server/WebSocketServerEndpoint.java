package com.test.websocket.server;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import static javax.websocket.CloseReason.CloseCodes.NORMAL_CLOSURE;

@ServerEndpoint("/")
public class WebSocketServerEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Session is opened.");
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("Client message: " + message);

        if ("Goodbye, server!".equals(message)) {
            session.close(new CloseReason(NORMAL_CLOSURE, "Goodbye, client!"));
        } else {
            session.getBasicRemote().sendText("Hello, client!");
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session is closed.");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error is occurred: " + throwable.getMessage());
    }
}
