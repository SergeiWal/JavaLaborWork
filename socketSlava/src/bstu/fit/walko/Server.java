package bstu.fit.walko;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final String DAY = "/day";
    public static final String GET_NUMBER = "/getnumber";
    public static final String HELP = "/help";


    private static final int PORT = 3000;
    private volatile int count;
    private ServerSocket serverSocket;

    public void countIncrement(){
        count++;
    }

    public int getCount(){
        return count;
    }

    public Server() throws IOException {

        count = 0;
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server started ...");

        while (true){

            Socket client = serverSocket.accept();
            System.out.println(client.getInetAddress() + " connected....");

            new ServerThread(client,this).start();

        }
    }


    public static void main(String[] args) {
        try {
            Server server = new Server();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

