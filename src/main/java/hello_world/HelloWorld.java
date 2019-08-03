package hello_world;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class HelloWorld extends HttpServlet {
    /*Создадим и отправим 100 запросов на сервлет*/
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        URLConnection connection = new URL("http://localhost:8080/hw").openConnection();
                        connection.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
