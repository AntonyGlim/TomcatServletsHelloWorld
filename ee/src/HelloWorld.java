import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/*Сервлет - один экземпляр на все запросы*/
/*Все запросы обрабатываются в разных потоках он потоко не безопасен*/
@WebServlet("/hw")
public class HelloWorld extends HttpServlet {

    /*Выполняется единожды при создании сервлета*/
    @Override
    public void init() throws ServletException {
        System.out.println("init Method");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet Method");
        System.out.println("Thread: " + Thread.currentThread().getName());
        resp.getWriter().write("Hello World!!!!!!!!!!!+++++++++++");
    }

    /*Создадим и отправим 100 запросов на сервлет*/
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        URLConnection connection = new URL("http://localhost:8081/hw").openConnection();
                        connection.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /*метод обрабатывает все наши запросы*/
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service Method");
        super.service(req, res);
    }

    /*не стоит на него полагаться*/
    @Override
    public void destroy() {
        System.out.println("destroy Method");
    }
}
