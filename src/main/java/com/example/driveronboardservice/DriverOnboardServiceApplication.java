package com.example.driveronboardservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
*
* The @SpringBootApplication annotation is used in Spring Boot applications to indicate the main class that serves as the entry point for the application. It is a combination of three other annotations: @Configuration, @EnableAutoConfiguration, and @ComponentScan.

@Configuration indicates that the class contains configuration methods that should be processed by the Spring container during startup. These methods are typically used to define beans that can be injected into other parts of the application.

@EnableAutoConfiguration enables Spring Boot's auto-configuration feature, which automatically configures the application based on the dependencies that are present in the classpath. This greatly reduces the amount of boilerplate code that is required to set up a Spring application.

@ComponentScan scans the package and its sub-packages to find Spring-managed beans and components that are annotated with @Component, @Controller, @Service, and other related annotations. It helps to identify the Spring components automatically and allows them to be injected into other parts of the application.
* */
@SpringBootApplication
@EnableSwagger2
public class DriverOnboardServiceApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(DriverOnboardServiceApplication.class, args);
    }

}
