package com.example.driveronboardservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DocumentUploadResponse {
    private Long id;
    private String status;
    // Constructors, getters, and setters
}
