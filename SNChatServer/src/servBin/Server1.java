package servBin;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class Server1 {
    static volatile Map<String, Integer> clients = new HashMap<>();
    static volatile ArrayList<T> online = new ArrayList<>();
    private static Map<String,String> avatar = new HashMap<>();
    public static void main(String[] args) throws IOException {

        dataBaseConnect();
        Server2.startHtmlServer();

        kek();
    }

    static void newConnect(String nick) {
        sendMessageFromConsole("!newConnection "+nick);
    }

    static void offConnect(T t, String nick) {
        System.out.println(t.nick + " отключился!");
        for (int i = 0; i < online.size(); i++) {
            if (online.get(i).equals(t)) {
                online.remove(i);
                t.socket = null;
                t.interrupt();
                sendMessageFromConsole("!theOnline");
                sendMessageFromConsole("!disconnect "+nick);
            }

        }
    }

    static void sendMessageFromConsole(String m) {
        String[] temp = m.split(" ");
        switch (temp[0]){
            case ("!theOnline"):
                break;
            case ("!newConnection"):
                String msg =
                        "nc <div style=\"background-color: rgb(153, 153, 153);\"><span\n" +
                        "        style=\"font-weight: bold; color: red;\"> <img\n" +
                        "        style=\"border: 2px solid ; width: 50px; height: 50px;\"\n" +
                        "        src=\"https://vk.com/images/emoji/2764_2x.png\"\n" +
                        "        alt=\"da\" align=\"middle\" hspace=\"3\" vspace=\"3\"><span\n" +
                        "        style=\"font-family: Arial; font-style: italic;\">&nbsp;<span\n" +
                        "        style=\"text-decoration: underline;\">*</span></span></span><small\n" +
                        "        style=\"font-family: Arial;\"><span style=\"font-style: italic;\">["+new Date().getHours()+":"+new Date().getMinutes()+":"+new Date().getSeconds()+"]</span></small><span\n" +
                        "        style=\"font-family: Arial;\"><span style=\"font-weight: bold;\">  --&gt;</span>\n" +
                        "</span><span style=\"font-weight: bold; font-family: Arial;\">"+temp[1] +" Подключился к серверу! </span>\n" +
                        "</div><div></div>\n" +
                        "\n";
                if (online.size() != 0)
                    for (int i = 0; i < online.size(); i++) {
                        if (!online.get(i).isInterrupted()) {
                            online.get(i).sMessage(msg);
                        }
                        }
                break;
            case ("!disconnect"):
                if (online.size() != 0)
                    for (int i = 0; i < online.size(); i++) {
                        if (!online.get(i).isInterrupted()) {
                            online.get(i).sMessage("dis <div style=\"background-color: rgb(153, 153, 153);\"><span\n" +
                                    "style=\"font-weight: bold; color: black;\"> <img\n" +
                                    "style=\"border: 2px solid ; width: 50px; height: 50px;\"\n" +
                                    "src=\"https://vk.com/images/emoji/D83DDC94_2x.png\"\n" +
                                    "alt=\"da\" align=\"middle\" hspace=\"3\" vspace=\"3\"><span\n" +
                                    "style=\"font-family: Arial; font-style: italic;\">&nbsp;<span\n" +
                                    "style=\"text-decoration: underline;\">*</span></span></span><small\n" +
                                    "style=\"font-family: Arial;\"><span style=\"font-style: italic;\">["+new Date().getHours()+":"+new Date().getMinutes()+":"+new Date().getSeconds()+"]</span></small><span\n" +
                                    "style=\"font-family: Arial;\"><span style=\"font-weight: bold;\"> --&gt;</span>\n" +
                                    "</span><span style=\"font-weight: bold; font-family: Arial;\">"+temp[1]+" Отключился!"+"</span>\n" +
                                    "</div><div></div>\n");
                        }
                    }
                break;
            case ("!allert"):
                break;
        }
    }


    static void sendMessage(String nick, String m) {
        if (online.size() != 0)
            for (int i = 0; i < online.size(); i++) {
                if (!online.get(i).isInterrupted())
                    System.out.println("to "+online.get(i)+" - "+m);
                    online.get(i).sMessage(inHtml(nick,m));
            }
    }

    static void kek() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7788);
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
        String query = "select nickname,password,avatar from users;";

        String url = "jdbc:mysql://den1.mysql6.gear.host:3306/mydb241";
        String user = "mydb241";
        String password = "Sambek241_";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nick = rs.getString("nickname");
                System.out.println(nick);
                String pass = rs.getString("password");
                String avatarURL = rs.getString("avatar");
                System.out.println(avatarURL);

                int hash = Arrays.hashCode(pass.toCharArray());
                System.out.println(hash + "\n");
                clients.put(nick, hash);
                avatar.put(nick,avatarURL);

            }
            System.out.println("Считывание бд завершено успешно!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("База данных не была подключена!");
        }

    }
    private static String inHtml(String nick, String text){
        Date date = new Date();
        text = replacer(text);
        String url = avatar.get(nick);
        text = text.replace("#!","<span\n" +
                "style=\"text-decoration: underline;\">");
        text = text.replace("!#","</span>");
        String[] kek= text.split("");
        if (kek.length>41){
            String temp="<p style=\"font-weight: bold; font-family: Arial;\">";
            for (int i = 0; i < kek.length; i+=1) {
                temp+=kek[i];
                if (i%41==0&&i!=0) temp+="</p><p style=\"font-weight: bold; font-family: Arial;\">";
            }
            temp+="</p><p></p>";
            text = temp;
        }

        String xx = "<div style=\"background-color: rgb(153, 153, 153);\"><span\n" +
                "style=\"font-weight: bold; color: black;\"> <img\n" +
                "style=\"border: 2px solid ; width: 50px; height: 50px;\"\n" +
                "src=\""+url+"\"\n" +
                "alt=\"da\" align=\"middle\" hspace=\"3\" vspace=\"3\"><span\n" +
                "style=\"font-family: Arial; font-style: italic;\">&nbsp;<span\n" +
                "style=\"text-decoration: underline;\">"+nick+"</span></span></span><small\n" +
                "style=\"font-family: Arial;\"><span style=\"font-style: italic;\">["+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"]</span></small><span\n" +
                "style=\"font-family: Arial;\"><span style=\"font-weight: bold;\"> :</span>\n" +
                "</span><span style=\"font-weight: bold; font-family: Arial;\">"+text+"</span>\n" +
                "</div><div></div>\n"+
                "\n";
        System.out.println(xx);
        return xx;
    }
    static String replacer(String text) {

        Map<String,String> kek = new HashMap<>();
        kek.put("<","&lt;");
        kek.put(">","&gt;");
        kek.put("хуй", flower());
        kek.put("пизда", flower());
        kek.put("сука", flower());
        kek.put("блять", flower());
        kek.put("ебать", flower());
        kek.put("ебал",flower());
        kek.put("ебанный", flower());
        kek.put("ёбаный", flower());
        kek.put("ёбанный", flower());
        kek.put("ебаный", flower());
        kek.put("пиздец", flower());
        kek.put("ахуел", flower());
        kek.put("ахуеть", flower());
        kek.put("пидор", flower());
        kek.put("член", flower());
        kek.put("ебала", flower());
        kek.put("елда", flower());
        kek.put("машонка", flower());
        kek.put("уебищ", flower());
        kek.put("уебищьный", flower());
        kek.put("нахуй", flower());
        kek.put("епта", flower());
        kek.put("ёпта", flower());
        kek.put("говно", flower());
        kek.put("моча", flower());
        String text1=text;
        for (Map.Entry entry : kek.entrySet()) {
            text = text.toLowerCase().replaceAll(entry.getKey().toString(), entry.getValue().toString());
        }

        String[] x1=text.split(" ");
        String[] x2=text1.split(" ");
        for (int i = 0; i < x1.length; i++) {
            if (!x1[i].equalsIgnoreCase(x2[i])){
                x2[i]=x1[i];
            }
        }
        text="";
        for (String s : x2) {
            text+=s+" ";
        }

        return text;
    }

    static String flower() {
        Random r = new Random();
        ArrayList<String> list = new ArrayList<>();
        list.add("Ромашка");
        list.add("Василёк");
        list.add("Одуванчик");
        list.add("Хризантема");
        list.add("Лютик");
        list.add("Роза");
        list.add("Тюльпан");
        list.add("Гвоздика");
        list.add("Гладиолус");
        list.add("Мимоза");
        list.add("Нарцисс");
        list.add("Пион");
        list.add("Орхидея");
        return list.get(r.nextInt(list.size()));
    }


}



