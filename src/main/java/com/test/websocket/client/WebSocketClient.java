package com.test.websocket.client;

import javax.websocket.ContainerProvider;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class WebSocketClient {

    private final String serverUrl;

    public WebSocketClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public static void main(String[] args) throws Exception {
        WebSocketClient websocketClient = new WebSocketClient("ws://localhost:8080");
        websocketClient.connectToServer();
    }

    public void connectToServer() throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        Session session = container.connectToServer(WebSocketClientEndpoint.class, new URI(serverUrl));

        RemoteEndpoint.Basic serverEndpoint = session.getBasicRemote();
        serverEndpoint.sendText("Hello, server!");
        serverEndpoint.sendText("Goodbye, server!");
    }
}
