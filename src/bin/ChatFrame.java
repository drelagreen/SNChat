package bin;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ChatFrame extends bin.Abstractions.ChatFrame {
    //
   String html = "";

    ChatFrame() {
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
    public void write(String text){
        html = text + html;
        chatField.setText(html);
    }
    public void tittle(){
        new Thread(()->{
           while (true){
               setTitle("SNchat");
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
