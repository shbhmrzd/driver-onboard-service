package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.Driver;

public interface DriverService {

    Driver createDriver(Driver driver);

    Driver updateDriver(String username, Driver driver);

    Driver updateIsReadyForRides(String username, Boolean isReady);

    void deleteDriver(long id);

    Driver getDriverById(long id);

    Driver getDriverByUsername(String username);

}
