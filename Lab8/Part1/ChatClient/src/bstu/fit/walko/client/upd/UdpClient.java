package bstu.fit.walko.client.upd;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
             socket = new DatagramSocket();
             String message="Hello, world";
             DatagramPacket packet = new DatagramPacket(message.getBytes(StandardCharsets.UTF_8),
                    message.length(), InetAddress.getLocalHost(),3000);
             socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
