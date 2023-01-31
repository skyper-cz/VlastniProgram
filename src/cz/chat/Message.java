package cz.chat;

import java.io.IOException;
import java.net.*;

public class Message {
    public static void Zprava(String adresa, int port, String zprava) throws UnknownHostException, SocketException {

        InetAddress address = InetAddress.getByName(adresa);
        DatagramSocket socket = new DatagramSocket();

        try {
            byte[] buffer = zprava.getBytes();
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(request);
        }
        catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
