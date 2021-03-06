import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ChatFrame extends bin.Abstractions.ChatFrame {
    private boolean x = true;
    private JButton smilesButton;
    javax.swing.JEditorPane onlinePanel;
    private javax.swing.JLayeredPane smilesPanel;
    private String html = "";
    private TrayIcon trayIcon;
    private SystemTray tray;

    ChatFrame() {
        initOnlinePanel();
        smilesInit();

        trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("xs.png"), "EXIT FROM THE CHAT");
        tray = SystemTray.getSystemTray();
        trayIcon.setImageAutoSize(true);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println("TrayIcon could not be added.");
        }
        trayIcon.addActionListener(e -> System.exit(0));


        smilesButton = new JButton(new ImageIcon(getClass().getResource("/images/1f642.png")));
        JToggleButton trayMBut = new JToggleButton(new javax.swing.ImageIcon(getClass().getResource("/images/2755_2x.png")));
        trayMBut.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2757_2x.png")));

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
        trayMBut.addActionListener(e -> {
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

        });

        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        button.setText(null);
        button.setIcon(new ImageIcon(getClass().getResource("/images/send.png")));
        button.setBorder(null);

        chatField.setAutoscrolls(true);
        chatField.setText("<div></div>");
        setTitle("SNChat");
        chatField.setSize(1000,1000);
        setSize(730,530);
        this.setLocationRelativeTo(null);

        button.addActionListener(e -> {
            sendMessage();
            if (!x) smilesButton.doClick();
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
        smilesButton.addActionListener(e -> {
            if (!x){
                hzPanel.remove(smilesPanel);
                initOnlinePanel();
                hzPanel.updateUI();
                smilesButton.updateUI();
                Kek.getServerMessage();
                x=!x;
            } else{
                smilesInit();
                hzPanel.add(smilesPanel,1);
                x=!x;
            }
        });
        hzPanel.remove(smilesPanel);
        initOnlinePanel();
        hzPanel.updateUI();
        smilesButton.updateUI();
        onlinePanel.setText(Kek.getServerMessage());

    }


    private void sendMessage(){
        String s = messageField.getText();
        Kek.outToServer(s);
        messageField.setText(null);
    }
    void write(String text) {

        String temp[] = text.split(" ");
        switch (temp[0]) {
            case "dis": {
                trayIcon.displayMessage("SNChat", "Один из участников чата отключился", TrayIcon.MessageType.NONE);
                temp[0] = "";
                StringBuilder temp2 = new StringBuilder();
                for (String s : temp) {
                    temp2.append(s).append(" ");
                }
                text = temp2.toString();
                break;
            }
            case "nc": {
                temp[0] = "";
                StringBuilder temp2 = new StringBuilder();
                for (String s : temp) {
                    temp2.append(s).append(" ");
                }
                text = temp2.toString();
                if (!html.equalsIgnoreCase(""))
                    trayIcon.displayMessage("SNChat", "Новое подключение!", TrayIcon.MessageType.NONE);

                break;
            }
            case "sm": {
                temp[0] = "";
                StringBuilder temp2 = new StringBuilder();
                for (String s : temp) {
                    temp2.append(s).append(" ");
                }
                text = temp2.toString();
                if (!x) smilesButton.doClick();
                Kek.setServerMessage(text);
                text = "";
                break;
            }
        }
        if(!text.equals("")) {
            html = text + html;
            chatField.setText(html);
        }
    }

    private void smilesInit() {
        smilesPanel = new javax.swing.JLayeredPane();
        JButton js1 = new JButton();
        JButton js2 = new JButton();
        JButton js3 = new JButton();
        JButton js4 = new JButton();
        JButton js5 = new JButton();
        JButton js6 = new JButton();
        JButton js7 = new JButton();
        JButton js8 = new JButton();
        JButton js9 = new JButton();
        JButton js10 = new JButton();
        JButton js11 = new JButton();
        JButton js12 = new JButton();


        smilesPanel.setBackground(new java.awt.Color(153, 153, 153));

        js1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile00.png"))); // NOI18N
        js1.addActionListener(evt -> messageField.setText(messageField.getText()+":fun:"));

        js2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile01.png"))); // NOI18N
        js2.addActionListener(evt -> messageField.setText(messageField.getText()+":yeah:"));
        js3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile02.png"))); // NOI18N
        js3.addActionListener(evt -> messageField.setText(messageField.getText()+":yee:"));
        js4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile03.png"))); // NOI18N
        js4.addActionListener(evt -> messageField.setText(messageField.getText()+":cool:"));
        js5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile04.png"))); // NOI18N
        js5.addActionListener(evt -> messageField.setText(messageField.getText()+":chmok:"));
        js6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile05.png"))); // NOI18N
        js6.addActionListener(evt -> messageField.setText(messageField.getText()+":wow:"));
        js7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile06.png"))); // NOI18N
        js7.addActionListener(evt -> messageField.setText(messageField.getText()+":XD:"));
        js8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile07.png"))); // NOI18N
        js8.addActionListener(evt -> messageField.setText(messageField.getText()+":verysad:"));
        js9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile08.png"))); // NOI18N
        js9.addActionListener(evt -> messageField.setText(messageField.getText()+":sad:"));
        js10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile09.png"))); // NOI18N
        js10.addActionListener(evt -> messageField.setText(messageField.getText()+":angry:"));
        js11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile10.png"))); // NOI18N
        js11.addActionListener(evt -> messageField.setText(messageField.getText()+":hmm:"));
        js12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/smiles/smile11.png"))); // NOI18N
        js12.addActionListener(evt -> messageField.setText(messageField.getText()+":smert:"));

        smilesPanel.setLayer(js1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        smilesPanel.setLayer(js7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout smilesPannelLayout = new javax.swing.GroupLayout(smilesPanel);
        smilesPanel.setLayout(smilesPannelLayout);
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
                                .addComponent(smilesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        hzPanelLayout.setVerticalGroup(
                hzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hzPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(smilesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())

        );
    }
    private void initOnlinePanel(){
        JScrollPane jScrollPane2 = new JScrollPane();
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
        onlinePanel.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
    }
}