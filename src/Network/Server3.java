package Network;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Server3 {
    ServerSocket mServer;
    int serverPort = 9090;

    InputStream fromClientStream;
    OutputStream toClientStream;

    DataInputStream reader;
    PrintWriter writer;

    public Server3() {

        try {
            // create server socket!
            mServer = new ServerSocket(serverPort);

            System.out.println("Server Created!");

            // wait for client
            // hold an object of Socket for each client
            Socket client = mServer.accept();

            // horaaaaa
            System.out.println("Connected to New Client!");

            // input stream (stream from client)
            fromClientStream = client.getInputStream();

            // output stream (stream to client)
            toClientStream = client.getOutputStream();

            reader = new DataInputStream(fromClientStream);
            writer = new PrintWriter(toClientStream, true);

            // send message to client
            writer.println("What is your name?");
            System.out.println("Server :What is your name?");

            // Receive client response (javab=name:D)
            String name = reader.readLine();


            while(true){
                //read command from client
                String command=reader.readLine();

                //now decide by command ;-)
                if(command.equals("BYE")){

                    System.out.println("Good Bye "+name);
                    break;

                }else if(command.equals("ADD")){

                    //read operands a,b from client
                    String a=reader.readLine();
                    String b=reader.readLine();

                    System.out.println(name+" :[ADD "+a+","+b+"]");
                    //calculate (a+b) then send it to client
                    writer.println(add(a,b));

                }else if(command.equals("EVAL")){
                    //read expression
                    String expression=reader.readLine();
                    //evaluate the expression
                    String ans=eval(expression);
                    System.out.println(name+" EVAL ["+expression+"]="+ans);

                    //send result to client
                    writer.println(ans);

                }else if(command.equals("ECHO")){
                    //read message from client
                    String msg=reader.readLine();

                    System.out.println(name+"ECHO ["+msg+"]");

                    //echo message !
                    writer.println("["+msg+"]");

                }
            }
        } catch (IOException e) {

        }

    }

    private String add(String a,String b){
        return ""+(Integer.parseInt(a)+Integer.parseInt(b));
    }

    private String eval(String expression){
        //evaluate mathematical expression by java script ;-)

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String result="";
        try {
            result=engine.eval(expression).toString();
        } catch (ScriptException e) {
        }
        return result;
    }

    public static void main(String[] args) {
        new Server();
    }
}