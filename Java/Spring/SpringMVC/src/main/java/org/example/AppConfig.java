package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class AppConfig {
    @Bean
    // This method returns a ViewResolver bean, which resolves logical view names to actual views.
    public ViewResolver getViewResolver() {
        // This resolver resolves view names to JSP files located in "/WEB-INF/jsp/" directory.
        // The suffix ".jsp" is added to the resolved view name.
        return new InternalResourceViewResolver("/WEB-INF/jsp/",".jsp");
    }
}