package Network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket mSocket;
    private int port = 9090;
    private String serverAddress = "127.0.0.1";

    public Client() {
        try {

            mSocket = new Socket(serverAddress, port);

            System.out.println("connect to server ....");
        } catch (UnknownHostException e) {}
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Client();
    }
}
