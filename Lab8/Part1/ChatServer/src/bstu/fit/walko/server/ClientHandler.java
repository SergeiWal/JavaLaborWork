package bstu.fit.walko.server;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread{

    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    private static final int PORT = 3000;
    private Socket clientSocket;
    private static int USERS_COUNT = 0;

    public ClientHandler(Socket socket, Server server){
        try {
            this.server = server;
            USERS_COUNT++;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        server.sendMessageAllUsers("New user in the chat!!!");
        server.sendMessageAllUsers("Users count: " + USERS_COUNT);

        while (true) {
            try {
                if(inMessage.hasNext()){
                    String clientMessage = inMessage.nextLine();

                    if(clientMessage.contains("#end")){
                        this.close();
                        break;
                    }
                    else{
                        server.sendMessageAllUsers(clientMessage);
                    }
                }
                Thread.sleep(100);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void sendMessage(String message){
        outMessage.println(message);
        outMessage.flush();
    }

    public void close(){
        server.removeUser(this);
        USERS_COUNT--;
        server.sendMessageAllUsers("Users count: " + USERS_COUNT);
    }

}
