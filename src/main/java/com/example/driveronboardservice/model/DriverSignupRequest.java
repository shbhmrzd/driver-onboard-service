package com.example.driveronboardservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DriverSignupRequest {
    private String name;
    private String phone;
    private String email;
    private String licenseNumber;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleYear;

    // Constructors, getters, and setters
}

