package com.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private final String host;
    private final int port;

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 10067);
        client.startConnection();

        String response = client.sendMessage("hello server");
        System.out.println(response);

        response = client.sendMessage("new message");
        System.out.println(response);

        response = client.sendMessage(".");
        System.out.println(response);

        client.stopConnection();
    }

    public void startConnection() throws IOException {
        this.clientSocket = new Socket(host, port);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String message) throws IOException {
        out.println(message);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
