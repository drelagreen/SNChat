package recycle.bin;


import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_ENTER;

/**
 * @author Drelgreen
 * @// TODO: 16.10.2018 textArea1 - chat; textArea2 - mArea; textArea3 - info; b1 - button
 *
 *
 */

public class Graph2 extends Gu {
        JDialog jDialog = new JDialog();
        Graph2(){
            textArea1.setAutoscrolls(true);
            textArea1.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {

                }
            });
            setTitle(Kek.VERSION);
            setVisible(true);
            textArea1.setSize(1,1);
            jScroll1.setAutoscrolls(true);
            StyledEditorKit cl = new StyledEditorKit();
            cl.install(textArea1);
            setSize(610,650);
            setBackground(new Color(0xB7F8AF));
            setResizable(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            b1.addActionListener(event -> vector());
            textArea2.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode()== VK_ENTER&&!e.isShiftDown()){
                        b1.doClick();
                    }
                    if (e.getKeyCode()== VK_ENTER&&e.isShiftDown()){
                        textArea2.setText(textArea2.getText()+"\n");
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode()== VK_ENTER&&!e.isShiftDown()){
                        textArea2.setText(null);
                    }
                }
            });

            this.setLocationRelativeTo(null);


        }
    private ArrayList<String> allTexts = new ArrayList<>();

    public void print(String text) {
        String s1 = " <head>\n" +
                "    \n" +
                "  </head>\n" +
                "  <body> \n";
        String s2 = " </body>\n" +
                "</html>\n";
        allTexts.add(text+"\n");
        StringBuilder messages = new StringBuilder();
        for(Object theText : allTexts) {
            messages.append("<p><font size=\"4\" face=\"Arial\">"+theText+"</font></p>\n");
        }
        textArea1.setText(s1+messages+"\n"+s2);

    }
    void print2(String text){
            textArea3.setText(text);
    }
    public String writeM() {
        String text;
        text = textArea2.getText();
        textArea2.setText(null);
        return text;
    }
    public void vector() {
        String text = writeM();
        String[] t = text.split("");
        if (t[0].equals("/")) {
            String x = "";
            for (int i = 1; i<t.length; i++){
                x+=t[i];
            }
            sChecker(x);
        } else {
            print(Kek.nickname+" > "+text);
            Kek.outToServer(text);
        }

    }
    public void sChecker(String text){

    }
//    void mda(){
//            progressBar1.setMaximum(10000);
//
//            new Thread(() -> {
//                while(true){
//                    if (progressBar1.getValue()==10000) progressBar1.setValue(0);
//                    progressBar1.setValue(progressBar1.getValue()+1);
//                    try {
//                        Thread.sleep(25);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//    }
}
