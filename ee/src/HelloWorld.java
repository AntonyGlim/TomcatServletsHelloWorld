import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        resp.getWriter().write("Hello World!!!!!!!!!!!+++++++++++");
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
