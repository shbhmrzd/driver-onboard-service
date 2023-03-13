package com.example.driveronboardservice.model;

import org.springframework.stereotype.Component;

@Component
public class TrackingDeviceRequest {
    private Long driverId;
    private String serialNumber;
    private String model;
    private String carrier;
    private String trackingNumber;

    // Constructors, getters, and setters
}
