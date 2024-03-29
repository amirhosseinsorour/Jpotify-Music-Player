package Network;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManager implements Runnable {
    Socket clientHolder;
    Server serverHolder;
    InputStream fromClientStream;
    OutputStream toClientStream;

    DataInputStream reader;
    PrintWriter writer;
    String IP;

    public ClientManager(Server server, Socket client) {
        serverHolder = server;
        clientHolder = client;

    }

    public void run() {
        try {
            // input stream (stream from client)
            fromClientStream = clientHolder.getInputStream();

            // output stream (stream to client)
            toClientStream = clientHolder.getOutputStream();

            reader = new DataInputStream(fromClientStream);
            writer = new PrintWriter(toClientStream, true);

            // send message to client
            writer.println("What is your IP?");
            System.out.println("Server :What is your IP?");

            // Receive client response (javab=name:D)
            IP = reader.readLine();

            // add "this" to Server "clientsMap" HashMap
            serverHolder.addClientManager(IP, this);

            while (true) {
                // read command from client
                String command = reader.readLine();

                // now decide by command ;-)
                if (command.equals("BYE")) {

                    System.out.println("Good Bye " + IP);
                    break;

                } else if (command.equals("ADD")) {

                    // read operands a,b from client
                    String a = reader.readLine();
                    String b = reader.readLine();

                    System.out.println(IP + " :[ADD " + a + "," + b + "]");
                    // calculate (a+b) then send it to client
                    writer.println("RESULT");
                    writer.println(add(a, b));

                } else if (command.equals("EVAL")) {
                    // read expression
                    String expression = reader.readLine();
                    // evaluate the expression
                    String ans = eval(expression);
                    System.out.println(IP + " EVAL [" + expression + "]=" + ans);

                    // send result to client
                    writer.println("RESULT");
                    writer.println(ans);

                } else if (command.equals("ECHO")) {
                    // read message from client
                    String msg = reader.readLine();

                    System.out.println(IP+ "ECHO [" + msg + "]");

                    // echo message !
                    writer.println("RESULT");
                    writer.println("[" + msg + "]");

                } else if (command.equals("SCHT")) {


                    String to = reader.readLine();
                    String text = reader.readLine();

                    sendTextToAnotherClient(to, text);
                } else if (command.equals("GCHT")) {
                    String text = reader.readLine();

                    sendTextToAllClients(text);
                }




                 else if (command.equals("SFILE")) {
                    String fileName = reader.readLine();
                    String to = reader.readLine();

                    int fileLength = Integer.parseInt(reader.readLine());

                    byte[] fileData = new byte[fileLength];

                    reader.readFully(fileData);

                    sendFileToAnotherClient(fileName, to, fileData);
                }
            }
        } catch (Exception e) {
        }
    }

    private String add(String a, String b) {
        return "" + (Integer.parseInt(a) + Integer.parseInt(b));
    }

    private String eval(String expression) {
        // evaluate mathematical expression by java script ;-)

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String result = "";
        try {
            result = engine.eval(expression).toString();
        } catch (ScriptException e) {
        }
        return result;
    }

    private void sendTextToAnotherClient(String to, String text) {


        // first find another client ("to") ClientManager object
        ClientManager anotherClient = serverHolder.findClientManager(to);
        if (anotherClient == null)
            return;
        anotherClient.sendText(IP, text);
    }

    private void sendText(String from, String text) {
        writer.println("SCHT");
        writer.println(from);
        writer.println(text);
    }

    private void sendTextToAllClients(String text) {
        // find all client managers
        // and for each of them use sendText method to send message
        for (ClientManager cm : serverHolder.findAllClientManagers()) {
            cm.sendText(IP, text);
        }
    }



    private void sendFileToAnotherClient(String fileName, String to, byte[] fileData) {

        // first find another client ("to") ClientManager object
        ClientManager anotherClient = serverHolder.findClientManager(to);
        if (anotherClient == null)
            return;
        anotherClient.sendFile(IP, fileName, fileData);

    }

    private void sendFile(String from, String fileName, byte[] fileData) {
        try {
            writer.println("FILE");
            writer.println(from);
            writer.println(fileName);
            writer.println("" + fileData.length);

            toClientStream.write(fileData, 0, fileData.length);
            toClientStream.flush();// force to send data

        } catch (IOException e) {
        }
    }

}
