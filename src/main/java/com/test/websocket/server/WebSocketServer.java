package com.test.websocket.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.javax.server.config.JavaxWebSocketServletContainerInitializer;

import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;

public class WebSocketServer {

    private final int port;
    private final Server server;

    public WebSocketServer(int port) {
        this.port = port;
        this.server = buildJettyServer();
    }

    public void start() throws Exception {
        server.start();
        server.join();
    }

    public void stop() throws Exception {
        server.stop();
    }

    private Server buildJettyServer() {
        Server server = new Server(port);
        ServerConnector serverConnector = new ServerConnector(server);
        ServletContextHandler contextHandler = buildContextHandler();

        server.addConnector(serverConnector);
        server.setHandler(contextHandler);

        return server;
    }

    private ServletContextHandler buildContextHandler() {
        ServletContextHandler contextHandler = new ServletContextHandler(SESSIONS);
        contextHandler.setContextPath("/");
        configureContextHandler(contextHandler);
        return contextHandler;
    }

    private void configureContextHandler(ServletContextHandler contextHandler) {
        JavaxWebSocketServletContainerInitializer.configure(contextHandler, (servletContext, webSocketContainer) -> {
            webSocketContainer.setDefaultMaxBinaryMessageBufferSize(65535);
            webSocketContainer.addEndpoint(WebSocketServerEndpoint.class);
        });
    }
}
