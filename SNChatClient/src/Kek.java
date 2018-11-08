

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class Kek {
    private static volatile String currentServerMessage = "";
    private static volatile Socket socket;
    private static volatile DataInputStream dataInputStream;
    private static volatile DataOutputStream dataOutputStream;
    static boolean isConnected = false;
    private static volatile LoginFrame lf;
    private static volatile ChatFrame cf;

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

    static void turn2() {
        System.out.println("OK");
        lf.logButton.setEnabled(false);
        lf.setVisible(false);
        cf = new ChatFrame();
        cf.setVisible(true);
        fromServer();
    }


    static void outToServer(String text){
        try {
            dataOutputStream.writeUTF(text);
            System.out.println("Исходящее сообщение:" +text);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void fromServer(){
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
                        interrupt();
                    }
                }
            }
        }.start();


    }

    static String getServerMessage(){
        String text;
        text = currentServerMessage;
        return text;
    }

    static void setServerMessage(String s){
        currentServerMessage = s;
        try {
            cf.onlinePanel.setText(s);
        } catch (Exception ignored){

        }
    }

}
