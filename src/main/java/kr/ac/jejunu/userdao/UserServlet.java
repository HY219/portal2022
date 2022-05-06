package kr.ac.jejunu.userdao;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class UserServlet extends GenericServlet {
    private UserDao userDao;

    @Override
    public void destroy() {
        System.out.println("***** destroy *****");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.userdao.jejunu");
        this.userDao = applicationContext.getBean("userDao", UserDao.class);
        System.out.println("*********  init ***********"); //요청이 먼저 왔으니까 init을 찍었을 거고 (3)
        super.init();
    }

    @SneakyThrows
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        User user = userDao.get(id);
        res.setContentType("text/html; charset=UTF-8");
        String html = String.format("<html><h1>Hi %s</h1></html>", user.getName()) ;
        res.getWriter().println(html); //res.getWriter에 html을 집어넣는다.
        System.out.println("********* service ***********"); //그 다음 service를 찍었을 것이다. (4) //req, res 역할?

    }
}
