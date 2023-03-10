package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.Driver;

public abstract class DriverService {
    public abstract Driver getDriverById(Long driverId);

    public Driver signUp(Driver driver) {
        return null;
    }
}
