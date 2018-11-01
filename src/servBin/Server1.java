package servBin;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Server1 {
    static volatile Map<String, Integer> clients = new HashMap<>();
    static volatile ArrayList<T> online = new ArrayList<>();
    static volatile ServerSocket serverSocket;
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) throws IOException {

        dataBaseConnect();
        Server2.startHtmlServer();

        kek();
    }

    static void newConnect(String nick) {

    }

    static void offConnect(T t, String nick) {
        System.out.println(t.nick + " отключился!");
        for (int i = 0; i < online.size(); i++) {
            if (online.get(i).equals(t)) {
                online.remove(i);
                t.socket = null;
                t.interrupt();
                sendMessage3("!theOnline");
                /*todo СООБЩЕНИЕ ОБ ОТКЛЮЧЕНИИ ПОЛЬЗОВАТЕЛЯ*/
            }

        }
    }

    static void sendMessage3(String m) {

    }

    static void sendMessage(String nick, String m) {
        if (online.size() != 0)
            for (int i = 0; i < online.size(); i++) {
                if (!online.get(i).isInterrupted())

                    online.get(i).sMessage("1" + nick + " > " + m);

            }
    }

    static void kek() throws IOException {
        serverSocket = new ServerSocket(7788);
        while (true) {
            try {
                System.out.println("Ожидание подключений...");
                T t = new T(serverSocket.accept());
                t.start();

                System.out.println("Подключение установлено (+1)");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("/kek/ error");
                break;
            }
        }
    }

    static void dataBaseConnect() {
        System.out.println("Идет анализ базы данных...");
//        try {
//            Class.forName("mysql-connector-java-5.1.42-bin.jar");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        String query = "select nickname,password from users;";
        String url = "jdbc:mysql://den1.mysql6.gear.host:3306/mydb241";
        String user = "mydb241";
        String password = "Sambek241_";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nick = rs.getString("nickname");
                System.out.println(nick);
                String pass = rs.getString("password");

                int hash = Arrays.hashCode(pass.toCharArray());
                System.out.println(hash + "\n");
                clients.put(nick, hash);
                System.out.println("Считывание бд завершено успешно!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("База данных не была подключена!");
        }

    }


}




