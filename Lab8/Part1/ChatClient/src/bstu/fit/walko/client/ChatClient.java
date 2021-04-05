package bstu.fit.walko.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static final String HOST = "192.168.31.156";
    public static final int PORT = 3000;
    private Socket clientSocket;
    private Scanner inMessage;
    private PrintWriter outMessage;
    private String name;
    private Scanner scanner;

    public  String getName(){
        return name;
    }

    public ChatClient(){
        try {
            this.clientSocket = new Socket(HOST,PORT);
            this.inMessage = new Scanner(clientSocket.getInputStream());
            this.outMessage = new PrintWriter(clientSocket.getOutputStream());
            this.scanner = new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Please, write your name ...");
        name = scanner.nextLine();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String command = "";

                while (!command.equals("#end")){
                    if(scanner.hasNext()) {
                        command = scanner.nextLine();
                        sendMsg(command);
                    }
                }
                Thread.interrupted();
                System.exit(0);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(inMessage.hasNext()){
                        String message =inMessage.nextLine();
                        if(!message.contains("#") && !message.contains(name)){
                            System.out.println(message);
                        }
                        else if(message.contains("#"+name)){
                            System.out.println(message.replace("#" + name,"") + " [private]");
                        }
                    }
                }
            }
        }).start();

    }

    public void sendMsg(String message) {
        String messageStr = name + ": " + message;
        outMessage.println(messageStr);
        outMessage.flush();
    }

}
