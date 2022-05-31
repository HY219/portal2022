package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ViewResolverComposite;

@Configuration
@EnableWebMvc
@ComponentScan("kr.ac.jejunu")
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addIn
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().suffix(".jsp").prefix("WEB/");
    }
}
