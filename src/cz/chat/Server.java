package cz.chat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;

import static cz.chat.Main.*;

public class Server {
    public static void Serverovic() throws IOException {
                DatagramSocket socket = new DatagramSocket(9999);

                while (true) {
                    byte[] buffer = new byte[560000];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);

                    String message = new String(packet.getData(), 0, packet.getLength());
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();

                    System.out.println(address + ":" + port + ": " + message);

                    String odeslat = "";
                    for (int i = 0; i < 5; i++){
                        odeslat += String.valueOf(zprava).charAt(i);
                    }

                    if (odeslat.equals("http")){
                        URL imageUrl = new URL(message);
                        Image image = ImageIO.read(imageUrl);
                        ImageIcon icon = new ImageIcon(image);

                        obrazek = new JButton(icon);
                        obrazek.setBounds(0, 0, 300, 50);
                        fr.add(obrazek);
                    }
                    else {
                        for (int i = 9; i > 0; i--){
                            zpravy[i] = zpravy[i-1];
                        }

                        Zprava vlozit = new Zprava();
                        vlozit.setOdesilatel("Druhý");
                        vlozit.setObsah(message);

                        zpravy[0] = vlozit;

                        Aktualizace();
                    }
                }
            }
        }
