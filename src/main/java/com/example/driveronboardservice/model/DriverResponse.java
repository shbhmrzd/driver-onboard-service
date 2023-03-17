package com.example.driveronboardservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class DriverResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Long documentId;
    private boolean isEligibleForRides;

//    private Document license;
    private String trackingDeviceStatus;
    private String backgroundCheckStatus;

    private String vehicleMake;
    private String vehicleModel;
    private String vehicleYear;
}
