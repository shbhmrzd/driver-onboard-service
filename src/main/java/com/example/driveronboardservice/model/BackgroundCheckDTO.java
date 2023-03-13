package com.example.driveronboardservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BackgroundCheckDTO {
    private Long id;
    private String status;
    private String comments;

    // Constructors, getters, and setters
}
