package com.example.driveronboardservice.model;

import org.springframework.stereotype.Component;

@Component
public class BackgroundCheckRequest {
    private Long driverId;
    private boolean passed;
    private String comments;

    // Constructors, getters, and setters
}
