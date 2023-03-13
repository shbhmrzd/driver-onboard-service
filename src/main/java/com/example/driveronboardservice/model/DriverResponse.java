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

    private Document license;
    private TrackingDevice trackingDevice;
    private BackgroundCheck backgroundCheck;

    private String vehicleMake;
    private String vehicleModel;
    private String vehicleYear;
}
