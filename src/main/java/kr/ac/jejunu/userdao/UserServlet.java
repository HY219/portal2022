package kr.ac.jejunu.userdao;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class UserServlet extends GenericServlet {
    @Override
    public void destroy() {
        System.out.println("***** destroy *****");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("*********  init ***********"); //요청이 먼저 왔으니까 init을 찍었을 거고 (3)
        super.init();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");
        String html = "<html><h1>Hi 허윤호</h1></html>";
        res.getWriter().println(html); //res.getWriter에 html을 집어넣는다.
        System.out.println("********* service ***********"); //그 다음 service를 찍었을 것이다. (4) //req, res 역할?

    }
}
