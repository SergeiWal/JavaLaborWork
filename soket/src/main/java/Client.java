

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int PORT = 3000;

    private PrintStream out;
    private BufferedReader in;
    private Scanner scanner;
    private Socket client;

    public Client(){

        try {
            client = new Socket(InetAddress.getLocalHost(),PORT);
            out = new PrintStream(client.getOutputStream());
            in=new BufferedReader(new InputStreamReader(client.getInputStream()));
            scanner = new Scanner(System.in);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String message;
                        try {
                            if((message = in.readLine())!=null){
                                System.out.println(message);
                            }
                        } catch (IOException e) {
                            System.out.println(e.getStackTrace());
                        }
                    }
                }
            }).start();


            String command = "";
            while (!command.contains("/end")){
                if(scanner.hasNext()){
                    command = scanner.nextLine();
                    out.println(command);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
    }

}

