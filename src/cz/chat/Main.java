package cz.chat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static cz.chat.Message.Zprava;
import static cz.chat.Server.Serverovic;

public class Main {

    public static JFrame fr = new JFrame("Chat");
    public static JTextField zprava = new JTextField();
    public static JTextField ip = new JTextField("IP");
    public static JTextField port = new JTextField("port");
    public static JButton odeslat = new JButton("odeslat");
    public static JButton potvrdit = new JButton();
    public static JButton obrazek = new JButton();
    public static JLabel[] vypis = new JLabel[10];

    public static String ipina = "127.0.0.1";
    public static int portik = 5005;
    public static Zprava[] zpravy = new Zprava[10];

    public static String vlozenaipina = "0";
    public static String vlozenyportik = "0";

    public static void main(String[] args) {
        fr.setBounds(0, 0, 800, 600);
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr.setLayout(null);
        fr.setVisible(true);
        fr.setResizable(true);

        ip.setBounds(200, 100, 300, 50);
        ip.setVisible(true);
        fr.add(ip);

        port.setBounds(200, 160, 300, 50);
        port.setVisible(true);
        fr.add(port);

        potvrdit.setBounds(200, 220, 300, 50);
        potvrdit.setVisible(true);
        potvrdit.addActionListener(Main::Potvrzeni);
        fr.add(potvrdit);
        zprava.setBounds(10, 500, 400, 50);
        zprava.setVisible(false);
        fr.add(zprava);

        odeslat.setBounds(420, 500, 300, 50);
        odeslat.setVisible(false);
        odeslat.addActionListener(Main::Odeslat);

        obrazek.setBounds(0, 0, 300, 50);
        obrazek.setVisible(false);
        fr.add(obrazek);

        fr.update(fr.getGraphics());
    }

    public static void Potvrzeni(ActionEvent e) throws IOException {
        vlozenaipina = String.valueOf(ip);
        vlozenyportik = String.valueOf(port);

        ip.setVisible(false);
        port.setVisible(false);
        potvrdit.setVisible(false);

        zprava.setVisible(true);
        odeslat.setVisible(true);
        obrazek.setVisible(true);

        Aktualizace();

        Serverovic();
    }
    public static void Odeslat(ActionEvent e) throws SocketException, UnknownHostException {
        String odeslat = "";
        for (int i = 0; i < 5; i++){
            odeslat += String.valueOf(zprava).charAt(i);
        }

        if (!odeslat.equals("http")){
            for (int i = 9; i > 0; i--){
                zpravy[i] = zpravy[i-1];
            }
            Zprava vlozit = new Zprava();
            vlozit.setOdesilatel("já");
            vlozit.setObsah(String.valueOf(zprava));

            zpravy[0] = vlozit;

            Zprava(vlozenaipina, Integer.parseInt(vlozenyportik), String.valueOf(zprava));
            Aktualizace();
        }
    }

    public static void Aktualizace(){
        for (int i = 0; i < vypis.length;i++) {
            vypis[i].setBounds(260 + 60*i , 0, 200, 50);
            vypis[i].setText(zpravy[i].getOdesilatel() + ": " + zpravy[i].getObsah());
            vypis[i].setVisible(true);
            fr.add(vypis[i]);
        }
    }

}
