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

    int i = 0;
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
        synchronized (this){
            /*Демонстрация потокоопасности*/
            for (int j = 0; j < 1_000_000; j++) {
                i++;
            }
            System.out.println(i);
        }
        //req - отвечает за все что приходит от клиента
        System.out.println();
        System.out.println("Работа с параметрами");
        //Запрос будет иметь вид: http://localhost:8081/hw?name=First&one=1&two=2&two=22&two=222&two=2222&two=22222
        System.out.println(req.getParameter("name"));
        System.out.println(req.getParameter("one"));
        String[] twos = req.getParameterValues("two");
        for (String two : twos) {
            System.out.println(two);
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
