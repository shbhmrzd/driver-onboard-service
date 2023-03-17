package com.example.driveronboardservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*

Used to indicate that a JUnit test is a Spring Boot test,
and that Spring Boot should be used to configure the test environment.

* By default, @SpringBootTest loads the entire Spring application context,
including all configuration classes, and starts up an embedded web server
(if needed) to test the application in a real environment.
* This allows for comprehensive integration testing of the Spring Boot application.
* */
@SpringBootTest
class DriverOnboardServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
