

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class Kek {
    public static final String VERSION = "SambekChat";
    static volatile Socket socket;
    static volatile DataInputStream dataInputStream;
    static volatile DataOutputStream dataOutputStream;
    static boolean isConnected = false;
    static volatile LoginFrame lf;
    static volatile ChatFrame cf;

    public static void main(String[] args) {
        lf = new LoginFrame();
        lf.setVisible(true);

    }

    static void login(String log, char[] pas, String ip) {
        System.out.println("Попытка авторизации - " + log);
        int hash = Arrays.hashCode(pas);
        try {
            socket = new Socket(ip, 7788);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(log + ":" + hash);
            isConnected = dataInputStream.readBoolean();
            if (!isConnected) {
                socket = null;
                dataInputStream = null;
                dataOutputStream = null;
                System.out.println("NO CONNECTION");
            }
        } catch (IOException e) {
            isConnected = false;
            System.out.println("ERROR");
        }

    }

    public static void turn2() {
        System.out.println("OK");
        lf.logButton.setEnabled(false);
        lf.setVisible(false);
        cf = new ChatFrame();
        cf.setVisible(true);
        fromServer();
    }




//    public static void sCom() {
//        new Thread(() -> {
//           while (true){
//               try {
//                   String s = dataInputStream.readUTF();
//                   System.out.println("new email");
//                   String t[] = s.split("");
//                   switch (t[0]){
//                       case ("1"):
//                           StringBuilder x = new StringBuilder();
//                           t[0]="";
//                           for (String s1 : t) {
//                               x.append(s1);
//                           }
//                           g2.print(x.toString());
//                           break;
//                       case ("3"):
//                           StringBuilder y = new StringBuilder();
//                           t[0]="";
//                           for (String s1 : t) {
//                               y.append(s1);
//                           }
//                           g2.print2(y.toString());
//                           break;
//                       case "N":
//                           StringBuilder z = new StringBuilder();
//                           t[0]="";
//                           for (String s1 : t) {
//                               z.append(s1);
//                           }
//                           nickname = z.toString();
//                           break;
//
//                   }
//               } catch (IOException e) {
//                   e.printStackTrace();
//                   break;
//               }
//           }
//        }).start();
//    }

    public static void outToServer(String text){
        try {
            dataOutputStream.writeUTF(text);
            System.out.println("Исходящее сообщение:" +text);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void fromServer(){
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        String s = dataInputStream.readUTF();
                        System.out.println(s);
                        cf.write(s);

                    } catch (IOException e) {
                        e.printStackTrace();
                        while(true);
                    }
                }
            }
        }.start();


    }

}
