/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bin.Abstractions.NewJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author drelagreen
 */
public class LoginFrame extends NewJFrame {
    TrayIcon trayIcon;SystemTray tray;

    KeyListener kl = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                logButton.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };


    LoginFrame() {
        trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("xs.png"), "ВЫХОД ИЗ ЧАТА");
        tray = SystemTray.getSystemTray();
        trayIcon.setImageAutoSize(true);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        }
        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        this.setLocationRelativeTo(null);


        ipField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    htmlPane.setPage("http://" + ipField.getText() + ":7789");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        htmlPane.setContentType("text/html");
        htmlPane.setText("no connection!");
        try {
            htmlPane.setPage("http://" + ipField.getText() + ":7789");
        } catch (IOException e) {
            htmlPane.setText("No Connection");
        }


        ArrayList<Image> iCont = new ArrayList<Image>();
        iCont.add(new ImageIcon("images/favicon.ico").getImage());
        setIconImages(iCont);


        logField.addKeyListener(kl);
        passField.addKeyListener(kl);
        ipField.addKeyListener(kl);

        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = logField.getText();
                String ip = ipField.getText();
                char[] pass = passField.getPassword();

                if (!login.equals("") && !ip.equals(""))
                    Kek.login(login, pass, ip);

                if (Kek.isConnected) {
                    connectionLabel.setText(" Входим...");
                    tray.remove(trayIcon);
                    Kek.turn2();
                } else {

                    connectionLabel.setText(" Ошибка подключения! ");
                }
            }
        });


    }

}


