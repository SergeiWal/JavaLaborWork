package bstu.fit.walko;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerThread extends Thread {

    private BufferedReader in;
    private PrintStream out;
    private InetAddress addr;
    private Server server;

    public ServerThread(Socket client, Server server) throws IOException {

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintStream(client.getOutputStream());
        addr = client.getInetAddress();
        this.server = server;
    }

    @Override
    public void run() {
        executeCommand(Server.HELP);
        while (true){
            try {
                String command;
                if((command = in.readLine())!=null){
                    executeCommand(command);
                }
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }

    private void executeCommand(String command) {

        if(command.contains(Server.DAY)){
            String result = "";
            server.countIncrement();
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("EE:MM:YY");
            result =  "Count: " + server.getCount() + " Date:" + format.format(date) +" Commands: /help /getnumber /day";
            out.println(result);
        }
        else if(command.contains(Server.GET_NUMBER)){
            out.println("Count: " + server.getCount());
        }
        else if(command.contains(Server.HELP)){
            out.println("Commands: /help /getnumber /day");
        }
        else{
            out.println("Unvalidated command");
        }

    }
}

