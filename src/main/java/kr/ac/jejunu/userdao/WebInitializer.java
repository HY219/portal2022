package kr.ac.jejunu.userdao;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        ServletRegistration.Dynamic context = servletContext.addServlet("dispatcher"
        , new DispatcherServlet((applicationContext)));
        context.setLoadOnStartup(1);
        context.addMapping("/");
    }
}
