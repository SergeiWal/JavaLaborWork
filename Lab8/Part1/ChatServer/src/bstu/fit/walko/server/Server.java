package bstu.fit.walko.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static final int PORT = 3000;
    List<ClientHandler> users = new ArrayList<ClientHandler>();

    public Server(){
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started ...");

            while (true){
                clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                users.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                clientSocket.close();
                System.out.println("Server stopped work ... ");
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageAllUsers(String message){
        for(var user: users){
            user.sendMessage(message);
        }
    }


    public void removeUser(ClientHandler user){
        users.remove(user);
    }
}
