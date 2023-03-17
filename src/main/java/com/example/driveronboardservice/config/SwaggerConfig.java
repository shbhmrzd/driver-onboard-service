package com.example.driveronboardservice.config;
/*
* Configure the Swagger documentation framework in this project
* */
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
* This is a configuration class for the Swagger framework and
* that Swagger should be enabled for this project
* */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*
    * returns a bean that can be used by the Spring container.
    * It creates a new instance of the "Docket" class,
    * which is used to configure and customize the Swagger documentation
    * */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.driveronboardservice.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}