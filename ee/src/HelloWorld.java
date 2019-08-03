import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

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
//        System.out.println("doGet Method");
//        System.out.println("Thread: " + Thread.currentThread().getName());
        resp.getWriter().write("Hello World!!!!!!!!!!!+++++++++++");
//        synchronized (this){
//            /*Демонстрация потокоопасности*/
//            for (int j = 0; j < 1_000_000; j++) {
//                i++;
//            }
//            System.out.println(i);
//        }
//        //req - отвечает за все что приходит от клиента
//        System.out.println();
//        System.out.println("Работа с параметрами");
//        //Запрос будет иметь вид: http://localhost:8081/hw?name=First&one=1&two=2&two=22&two=222&two=2222&two=22222
//        System.out.println(req.getParameter("name"));
//        System.out.println(req.getParameter("one"));
//        String[] twos = req.getParameterValues("two");
//        for (String two : twos) {
//            System.out.println("value: " + two);
//        }
//        Enumeration<String> parameterNames = req.getParameterNames();//имена всех параметров
//        while (parameterNames.hasMoreElements()){
//            System.out.println("name: " + parameterNames.nextElement());
//        }
//        Map<String, String[]> parameterMap = req.getParameterMap();
//        System.out.println(req.getRequestURL());
//        System.out.println(req.getRequestURI());
//        System.out.println(req.getServletPath());
//        System.out.println(req.getRemoteHost());
//        System.out.println(req.getLocalPort());
//
//        System.out.println(req.getQueryString()); //name=First&one=1&two=2&two=22&two=222&two=2222&two=22222
//
//        String firstname = req.getParameter("firstname");
//        //защита от XSS атак
//        firstname = firstname == null ? null : firstname.replaceAll("<", "&lt").replaceAll(">", "&gt");
//        resp.getWriter().write("<!DOCTYPE html>\n" +
//                "<html lang=\"ru\">\n" +
//                "<head>\n" +
//                "\t<title>Контакты</title>\n" +
//                "\t<meta charset=\"utf-8\">\n" +
//                "\t<link href=\"styles/styles.css\" rel=\"stylesheet\">\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "\t<h1>Hello World!!!!!!!!!!!+++++++++++</h1>\n" +
//                "firstname= " + firstname + "<br>" +
//                "\t<form action=\"hw\" method=\"post\">\n" + /*get или post*/
//                "\t\tFirst name:<br>\n" +
//                "\t\t<textarea name=\"firstname\"></textarea><br>\n" +
//                "\t\t<input type=\"submit\" name=\"submit\">\n" +
//                "\t</form>\n" +
//                "</body>\n" +
//                "</html>");

//        Enumeration<String> headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + "= " + req.getHeader(headerName));
//        }

        /*gzip - Архивирует*/
//        if(req.getHeader("Accept_Encoding").contains("gzip")){
//            PrintWriter printWriter = new PrintWriter(new GZIPOutputStream(resp.getOutputStream()));
//            printWriter.write("hello world"); /*Теоретически это пошлет в браузер информацию в архиве*/
//        }
        /*статус для страницы*/
//        resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//        resp.sendRedirect("/pageAdress"); //перенаправит на страницу можно настроить через сколько секунд нас перекинет на другую страницу
//        resp.setHeader("Refresh", "1");//СТРАНИЦА БУДЕТ ОБНАВЛЯТЬСЯ КАЖДУЮ СЕКУНДУ
//        int i = 0;
//        System.out.println(i++);

        /*Создали куку*/
//        Cookie cook = new Cookie("Cooke", "test");
//        resp.addCookie(cook);
//        cook.setMaxAge(5); //через 5 секунд кука удалиться
//        cook.setPath("/onlyThisPageShowCookies.html");
//
//        Cookie[] cookies = req.getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println("name: " + cookie.getName());
//            System.out.println("value: " + cookie.getValue());
//        }

        /*Работа с сессиями*/
        HttpSession session = req.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String attributeName = attributeNames.nextElement();
            System.out.println(attributeName);
        }
        session.setAttribute("one", "two");
        System.out.println(session.getMaxInactiveInterval()); //время жизни сессии

    }

    /*Метод не будет светить данные в адресной строке (пароли)*/
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
