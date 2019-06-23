package Network;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client3 {
    Socket mSocket;
    int port = 9090;
    String serverAddress = "127.0.0.1";

    InputStream fromServerStream;
    OutputStream toServerStream;

    DataInputStream reader;
    PrintWriter writer;

    public Client3() {
        try {

            mSocket = new Socket(serverAddress, port);

            System.out.println("connect to server ....");

            // input stream (stream from server)
            fromServerStream = mSocket.getInputStream();

            // output stream (stream to server)
            toServerStream = mSocket.getOutputStream();

            reader = new DataInputStream(fromServerStream);
            writer = new PrintWriter(toServerStream, true);

            // first : read server message
            String msg = reader.readLine();
            System.out.println("Server :" + msg);

            menu();

        } catch (UnknownHostException e) {
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public void menu(){

        Scanner sc=new Scanner(System.in);

        String name=sc.nextLine();

        sendName(name);

        while(true){
            System.out.println("-----------");
            System.out.println("Enter Command number(ex:3):\n"
                    + "1)add two number\n"
                    + "2)evaluate an expression\n"
                    + "3)echo\n"
                    + "4)exit");

            int commandNumber=Integer.parseInt(sc.nextLine());

            switch(commandNumber){
                case 1:
                    //add two number
                    System.out.println("Enter a:");
                    String a=sc.nextLine();
                    System.out.println("Enter b:");
                    String b=sc.nextLine();

                    add(a,b);

                    break;
                case 2:
                    System.out.println("Enter expression (ex:2*3+4)");
                    String expression=sc.nextLine();

                    eval(expression);

                    break;
                case 3:
                    System.out.println("Enter message");
                    String msg=sc.nextLine();

                    echo(msg);

                    break;
                case 4:

                    bye();

                    return;

            }
        }
    }
    private void sendName(String name){
        writer.println(name);
    }
    private void add(String a,String b){
        writer.println("ADD");
        writer.println(a);
        writer.println(b);

        try {
            System.out.println(reader.readLine());
        } catch (IOException e) {
        }
    }
    private void echo(String msg){
        writer.println("ECHO");
        writer.println(msg);

        try {
            System.out.println(reader.readLine());
        } catch (IOException e) {
        }
    }
    private void eval(String expression){
        writer.println("EVAL");
        writer.println(expression);

        try {
            System.out.println(reader.readLine());
        } catch (IOException e) {
        }
    }
    private void bye(){
        writer.println("BYE");
    }
    public static void main(String[] args) {
        new Client3();
    }
}
