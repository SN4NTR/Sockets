package com.test.websocket.client;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class WebSocketClientEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Session is opened.");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Server message: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("Session is closed: " + reason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error is occurred: " + throwable.getMessage());
    }
}
