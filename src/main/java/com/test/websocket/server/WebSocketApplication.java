package com.test.websocket.server;

public class WebSocketApplication {

    public static void main(String[] args) throws Exception {
        WebSocketServer server = new WebSocketServer(8080);
        server.start();
    }
}
