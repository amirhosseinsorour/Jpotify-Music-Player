package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket mServer;
    private static final int serverPort = 9090;

    public Server() {

        try {
            // create server socket!
            mServer = new ServerSocket(serverPort);

            System.out.println("Server Created!");

            // wait for client
            mServer.accept();

            System.out.println("Connected to New Client!");
        } catch (IOException ignored) {

        }

    }

    public static void main(String[] args) {
        new Server();
    }
}
