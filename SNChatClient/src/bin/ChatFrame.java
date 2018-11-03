package bin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.peer.TrayIconPeer;


public class ChatFrame extends bin.Abstractions.ChatFrame {
    //
    private String html = "";
    private TrayIcon trayIcon;
    private SystemTray tray;
    ChatFrame() {
        trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("res/images/xs.png"), "ВЫХОД ИЗ ЧАТА");
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



        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        button.setText(null);


        button.setIcon(new ImageIcon("https://vk.com/images/emoji/2764_2x.png"));
        chatField.setAutoscrolls(true);
        chatField.setText("<div></div>");
        setTitle("SNchat");
        tittle();
    chatField.setSize(1000,1000);
    setSize(730,530);
    this.setLocationRelativeTo(null);

        button.addActionListener(e -> {
            sendMessage();
            messageField.setText("");
        });
        messageField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
            if (!e.isShiftDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                button.doClick();

            }
            if (e.isShiftDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                messageField.setText(messageField.getText()+"\n");
            }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!e.isShiftDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    messageField.setText(null);
                }
            }
        });

    }

    public void sendMessage(){
        String s = messageField.getText();
        Kek.outToServer(s);
        messageField.setText(null);
    }
    public void write(String text) {

        String temp[] = text.split(" ");
        if (temp[0].equals("dis")){
            trayIcon.displayMessage("SNChat","Один из участников чата отключился", TrayIcon.MessageType.NONE);
            temp[0]="";
            String temp2="";
            for (String s : temp) {
                temp2+=(s+" ");
            }
            text=temp2;
        } else if (temp[0].equals("nc")){
            if(!html.equalsIgnoreCase(""))
            trayIcon.displayMessage("SNChat","Новое подключение!", TrayIcon.MessageType.NONE);
            temp[0]="";
            String temp2="";
            for (String s : temp) {
                temp2+=s+" ";
            }
            text=temp2;
        }

        html = text + html;
        chatField.setText(html);
    }


    public void tittle(){
        new Thread(()->{
           while (true){
               this.setTitle("SNchat");
              try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               setTitle("sNChat");
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               setTitle("snCHat");
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               this.setTitle("sncHAt");
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               this.setTitle("snchAT");
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               this.setTitle("SnchaT");
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
    }
    }
