package Network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client2 {
    private Socket mSocket;
    private int port = 9090;
    private String serverAddress = "127.0.0.1";

    private InputStream fromServerStream;
    private OutputStream toServerStream;

    private InputStreamReader reader;
    private PrintWriter writer;

    public Client2() {
        try {

            mSocket = new Socket(serverAddress, port);

            System.out.println("connect to server ....");

            // input stream (stream from server)
            fromServerStream = mSocket.getInputStream();

            // output stream (stream to server)
            toServerStream = mSocket.getOutputStream();

            reader = new InputStreamReader(fromServerStream);
            writer = new PrintWriter(toServerStream, true);

            // first : read server message
            Scanner scanner = new Scanner(reader);
            String msg = scanner.nextLine();
            System.out.println("Server :" + msg);

            // send message to server
            writer.println("Salam Servere man");
            System.out.println("Client :Salam Servere man");

            // read server message
            msg = scanner.nextLine();
            System.out.println("Server :" + msg);

            // send message to server
            writer.println("Ohum!!!");
            System.out.println("Client :Ohum!!!");

        } catch (UnknownHostException e) {
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Client();
    }
}
