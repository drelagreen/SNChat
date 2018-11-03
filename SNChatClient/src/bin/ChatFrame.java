package bin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ChatFrame extends bin.Abstractions.ChatFrame {
    boolean x = true;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JEditorPane onlinePanel;
    private javax.swing.JLayeredPane smilesPannel;
    private javax.swing.JButton js1;
    private javax.swing.JButton js10;
    private javax.swing.JButton js11;
    private javax.swing.JButton js12;
    private javax.swing.JButton js2;
    private javax.swing.JButton js3;
    private javax.swing.JButton js4;
    private javax.swing.JButton js5;
    private javax.swing.JButton js6;
    private javax.swing.JButton js7;
    private javax.swing.JButton js8;
    private javax.swing.JButton js9;

    private String html = "";
    private TrayIcon trayIcon;
    private SystemTray tray;
    ChatFrame() {
        initOnlinePanel();
        smilesInit();


//        JTextPane online = new JTextPane();
//        hzPanel.add(new JScrollPane(online));
//        online.setText("ddddddddddddddddddddddddddssssssssssssssssssssssssssssddddddddddddddddddddddddsssssssssssss");


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

        JButton smilesButton = new JButton(new javax.swing.ImageIcon(getClass().getResource("1f642.png"))) ;
        JToggleButton trayMBut = new JToggleButton(new javax.swing.ImageIcon(getClass().getResource("2755_2x.png")));
        trayMBut.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("2757_2x.png")));

        javax.swing.GroupLayout hzPanel2Layout = new javax.swing.GroupLayout(hzPanel2);
        hzPanel2.setLayout(hzPanel2Layout);
        hzPanel2Layout.setHorizontalGroup(
                hzPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(hzPanel2Layout.createSequentialGroup()
                                .addComponent(smilesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(trayMBut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 36, Short.MAX_VALUE))
        );
        hzPanel2Layout.setVerticalGroup(
                hzPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(hzPanel2Layout.createSequentialGroup()
                                .addGroup(hzPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(smilesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(trayMBut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 60, Short.MAX_VALUE))
        );
        trayMBut.doClick();
        trayMBut.setBackground(new Color(102,102,102));
        trayMBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!trayMBut.isSelected())
                tray.remove(trayIcon);
                else {
                    try {
                        tray.add(trayIcon);
                        trayIcon.displayMessage("Переключатель","Вы включили всплывающие уведомления!", TrayIcon.MessageType.NONE);
                    } catch (AWTException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });

        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        button.setText(null);
        button.setIcon(new ImageIcon(getClass().getResource("/images/send.png")));
        button.setBorder(null);

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
    smilesButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!x){
                hzPanel.remove(smilesPannel);
                initOnlinePanel();
                hzPanel.updateUI();
                smilesButton.updateUI();
                onlinePanel.setText("kakakakakakakakakakakaakakaka\n"+"kakakakakakakakakakakaakakaka\n");
            x=!x;
            } else{
                smilesInit();
                hzPanel.add(smilesPannel,1);
                x=!x;
            }
        }
    });
        hzPanel.remove(smilesPannel);
        initOnlinePanel();
        hzPanel.updateUI();
        smilesButton.updateUI();
        onlinePanel.setText("kakakakakakakakakakakaakakaka\n"+"kakakakakakakakakakakaakakaka\n");

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

    void smilesInit() {
        smilesPannel = new javax.swing.JLayeredPane();
        js1 = new javax.swing.JButton();
        js2 = new javax.swing.JButton();
        js3 = new javax.swing.JButton();
        js4 = new javax.swing.JButton();
        js5 = new javax.swing.JButton();
        js6 = new javax.swing.JButton();
        js7 = new javax.swing.JButton();
        js8 = new javax.swing.JButton();
        js9 = new javax.swing.JButton();
        js10 = new javax.swing.JButton();
        js11 = new javax.swing.JButton();
        js12 = new javax.swing.JButton();


        smilesPannel.setBackground(new java.awt.Color(153, 153, 153));

        js1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile00.png"))); // NOI18N
        js1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        js2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile01.png"))); // NOI18N
        js2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile02.png"))); // NOI18N
        js3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile03.png"))); // NOI18N
        js4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile04.png"))); // NOI18N
        js5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile05.png"))); // NOI18N
        js6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile06.png"))); // NOI18N
        js7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile07.png"))); // NOI18N
        js8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile08.png"))); // NOI18N
        js9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile09.png"))); // NOI18N
        js10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile10.png"))); // NOI18N
        js11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        js12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bin/smiles/smile11.png"))); // NOI18N
        js12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        smilesPannel.setLayer(js1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPannel.setLayer(js7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout smilesPannelLayout = new javax.swing.GroupLayout(smilesPannel);
        smilesPannel.setLayout(smilesPannelLayout);
        smilesPannelLayout.setHorizontalGroup(
                smilesPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, smilesPannelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(smilesPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, smilesPannelLayout.createSequentialGroup()
                                                .addComponent(js1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(js2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(js3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(js4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(smilesPannelLayout.createSequentialGroup()
                                                .addComponent(js5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(js6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(js7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(js8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(smilesPannelLayout.createSequentialGroup()
                                                .addComponent(js9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(js10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(js11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(js12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        smilesPannelLayout.setVerticalGroup(
                smilesPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(smilesPannelLayout.createSequentialGroup()
                                .addGroup(smilesPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(js1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(smilesPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(js5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(smilesPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(js9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout hzPanelLayout = new javax.swing.GroupLayout(hzPanel);
        hzPanel.setLayout(hzPanelLayout);
        hzPanelLayout.setHorizontalGroup(
                hzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hzPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(smilesPannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        hzPanelLayout.setVerticalGroup(
                hzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hzPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(smilesPannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())

        );
    }
    void initOnlinePanel(){
        jScrollPane2 = new javax.swing.JScrollPane();
        onlinePanel = new javax.swing.JEditorPane();
        onlinePanel.setEditable(false);
        onlinePanel.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(onlinePanel);
        javax.swing.GroupLayout hzPanelLayout = new javax.swing.GroupLayout(hzPanel);
        hzPanel.setLayout(hzPanelLayout);
        hzPanelLayout.setHorizontalGroup(
                hzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );
        hzPanelLayout.setVerticalGroup(
                hzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
        );
    }
    }
