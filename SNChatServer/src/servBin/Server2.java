package servBin;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server2 {
//    static String html="\n" +
//            "<!DOCTYPE html>\n" +
//            "<html lang=\"en\">\n" +
//            "<head>\n" +
//            "    <meta charset=\"UTF-8\">\n" +
//            "    <title>Html</title>\n" +
//            "    <link rel=\"stylesheet\" href=\"html.css\">\n" +
//            "</head>\n" +
//            "<body>\n" +
//            "    <h1>SambekChat Server 1</h1>\n" +
//            "    <p>Online:</p>\n" +
//            "\n" +
//            "</body>\n" +
//            "</html>";

//    static String htmlHeader =
//            "HTTP/1.1 200 OK" +
//            "Content-Type: text/html; charset=UTF-8" +
//            "Content-Length: "+html.length();

    public static void startHtmlServer() throws IOException {
        HttpServer server = HttpServer.create();

        server.bind(new InetSocketAddress(7789), 0);
        server.createContext("/", new sh());
        server.start();
    }

    static class sh implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder builder = new StringBuilder();
            builder.append("<style type=\"text/css\">\n" +
                    "h1 {\n" +
                    "font-size: 32;\n" +
                    "" +
                    "color: #333366;\n" +
                    "}\n" +
                    "p{\n" +
                    "font-family: Verdana, Arial, Helvetica, sans-serif;\n" +
                    "background-color: #A5DCFF; " +
                    "}\n" +
                    "body{\n" +
//                "background: #bfd7ff;\n" +
                    "}\n" +
                    ".X{\n" +
                    "color: red;\n" +
                    "font-size: 48;\n" +
                    "}" +
                    "</style>\n");
            builder.append("<h1>SambekChat server</h1>");
            builder.append("<p>Online - " + Server1.online.size() + ": </p>");
            for (T t : Server1.online) {
                builder.append("<p>" + t.nick + "</p>");
            }
            byte[] bytes = builder.toString().getBytes();
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();

        }
    }
}
