package com.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static java.util.Objects.nonNull;

public class Server {

    private final int port;

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(8080);
        server.start();
    }

    public void start() throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.clientSocket = serverSocket.accept(); // blocks until request

        onRequestReceived();
    }

    private void onRequestReceived() throws IOException {
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while (nonNull(inputLine = in.readLine())) {
            if (".".equals(inputLine)) {
                out.println("Good bye");
                stop();
                break;
            }

            out.println("I received your message: " + inputLine);
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
