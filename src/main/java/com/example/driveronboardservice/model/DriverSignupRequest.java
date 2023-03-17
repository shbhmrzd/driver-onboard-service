package com.example.driveronboardservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DriverSignupRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String licenseNumber;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleYear;
}

