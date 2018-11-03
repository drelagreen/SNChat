//package bin;
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class Graph extends JFrame {
//   JTextField jj;
//    Graph(){
//        setResizable(false);
//        setTitle(Kek.VERSION);
//        setSize(500,500);
//        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        JLabel label = new JLabel("Welcome to SAMBEKChat webEd(0.2b)");
//        label.setFont(new Font("Monaco", Font.PLAIN, 27));
//        JTextField login = new JTextField(10);
//        JPasswordField password = new JPasswordField(10);
//        JButton ok = new JButton("OK");
//
//        JButton cancel = new JButton("CANCEL");
//        setLocationRelativeTo(password);
//        JPanel panel1 = new JPanel();panel1.setLayout(new GridLayout(12,1));
//        panel1.setSize(500,500);
//
//        panel1.add(label);
//        panel1.add(new JLabel("          Login:"));
//        panel1.add(login);
//        panel1.add(new JLabel("          Password:"));
//        panel1.add(password);
////        panel1.add(new JLabel("          IP:"));
////        JTextField ipField  = new JTextField(10);
////        panel1.add(ipField);
//        panel1.add(ok);
//        panel1.add(cancel);
////        ipField.setFont(new Font("Monaco", Font.PLAIN, 20));
//        login.setFont(new Font("Monaco", Font.PLAIN, 20));
//        password.setFont(new Font("Monaco", Font.PLAIN, 20));
//
//
//        jj = new JTextField(10);
//        panel1.add(jj);
//        jj.setEditable(false);
//
//        setContentPane(panel1);
//
//
//        ok.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                if (!login.getText().equals("") &&password.getPassword()!=null/*&& !ipField.getText().equals("")*/)
//                Kek.login(login.getText(),password.getPassword(),"sambek.hopto.org"/* ipField.getText()*/);
//
//                if (Kek.isConnected) {jj.setText(":) Загрузка..."); Kek.turn2(); } else {
//
//                    jj.setText(":( Подключение не удалось  ");
//                }
//
//                ok.setEnabled(true);
//                cancel.setEnabled(true);
//            }
//        });
//
//        KeyListener kl = new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//                if (e.getKeyCode()==KeyEvent.VK_ENTER){
//                    ok.doClick();
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        };
//        ipField.addKeyListener(kl);
//        password.addKeyListener(kl);
//        login.addKeyListener(kl);
//
//        cancel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//
//
//         }
//}
