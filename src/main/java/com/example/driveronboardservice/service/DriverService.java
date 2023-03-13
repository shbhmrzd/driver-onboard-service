package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.Driver;

public interface DriverService {

    Driver createDriver(Driver driver);

    Driver updateDriver(long id, Driver driver);

    void deleteDriver(long id);

    Driver getDriverById(long id);
}
