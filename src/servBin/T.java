package servBin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class T extends Thread{
    volatile Boolean hmm = false;
    volatile Socket socket;
    volatile String nick;
    volatile DataInputStream dataInputStream;
    volatile DataOutputStream dataOutputStream;
    T(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String welcome = dataInputStream.readUTF();
            String w1[] = welcome.split(":");

            if (Server1.clients.get(w1[0])==Integer.parseInt(w1[1])){
                System.out.println(w1[0]+"\n"+w1[1]);

                for (int i = 0; i < Server1.online.size(); i++) {
                    if (Server1.online.get(i).nick.equals(w1[0])){
                        dataOutputStream.writeBoolean(false);
                        throw new IOException(); //todo КЛИЕНТ с ТАКИМ НИКОМ УЖЕ ПОДКЛЮЧЕН
                    }
                }
                dataOutputStream.writeBoolean(true);
                nick = w1[0];//todo NICKNAME
                dataOutputStream.writeUTF("N"+nick);

                Server1.online.add(this);
                inMessageDaemon();
                hmm=true;
                Server1.newConnect(nick);

            } else {
                System.out.println("wrongPass! de pas is - " + Server1.clients.get(w1[0]) + " but " + w1[1]);
                dataOutputStream.writeBoolean(false);
            }

        } catch (Exception e) {
            System.out.println("ERROR "+this.nick);
            e.printStackTrace();
            if (hmm)
            Server1.offConnect(this, nick);
        }
    }
    public void sMessage(String m){
        try {

            dataOutputStream.writeUTF(m);

        } catch (IOException e) {
            if(hmm) {
                System.out.println("ERROR "+this.nick);
                e.printStackTrace();
                Server1.offConnect(this, nick);
            }
        }
    }
    public void inMessageDaemon(){
        new Thread(()->{
            while (true){
                try {
                    String m = dataInputStream.readUTF();
                   Server1.sendMessage(nick, m);

                } catch (IOException e) {
                    System.out.println("ERROR "+this.nick);
                    Server1.offConnect(this, nick);
                    break;
                }
            }
        }).start();
    }
    }
