package bstu.fit.walko.server.udp;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpServer {

    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        try {
            System.out.println("Server started....");
            datagramSocket = new DatagramSocket(3000);
            byte arr[] = new byte[64];
            DatagramPacket packet = new DatagramPacket(arr, arr.length);
            while(true)
            {
                datagramSocket.receive(packet);
                if(packet.getLength()>0) {
                    String message =new String(packet.getData(), "UTF-8");
                    System.out.println(message);
                    break;
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            datagramSocket.close();
        }
    }
}
