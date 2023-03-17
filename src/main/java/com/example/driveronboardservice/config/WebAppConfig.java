package com.example.driveronboardservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
/*
* It implements the "WebMvcConfigurer" interface,
* which provides methods to customize the behavior of the Spring MVC framework.
* */
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    /*
    *  Add resource handlers to serve static resources
    * like HTML, CSS, JS, and images, which are required by Swagger UI
    * */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
        * The "addResourceHandlers()" method is used to register these handlers,
        * which map requests for static resources to the corresponding physical files on the server.
        * */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}