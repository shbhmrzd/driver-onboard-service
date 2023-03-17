package com.example.driveronboardservice.service;

import com.example.driveronboardservice.exception.DataNotFoundException;
import com.example.driveronboardservice.exception.OperationNotPermitted;
import com.example.driveronboardservice.exception.SignUpException;
import com.example.driveronboardservice.model.Driver;
import com.example.driveronboardservice.repository.DriverRepository;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.Optional;

@Service
public  class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        super();
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver createDriver(Driver driver) {
        try{
            return driverRepository.save(driver);
        }catch (Exception e){
            throw new SignUpException(e.getMessage());
        }

    }

    @Override
    public Driver updateDriver(String username, Driver driverRequest) {
        Driver driver = driverRepository.findByUsername(username).orElse(null);
        if(driver == null){
            throw new DataNotFoundException("No such driver for username : " + username);
        }
        driver.setVehicleMake(driverRequest.getVehicleMake());
        driver.setVehicleModel(driverRequest.getVehicleModel());
        driver.setVehicleYear(driverRequest.getVehicleYear());
        return driverRepository.save(driver);
    }

    @Override
    public Driver updateIsReadyForRides(String username, Boolean isReady) {
        Driver driver = driverRepository.findByUsername(username).orElse(null);
        if(driver == null){
            throw new DataNotFoundException("No such driver for username : " + username);
        }

        if(driver.getBackgroundCheck() == null || !driver.getBackgroundCheck().getStatus().equals("verified")){
            throw new OperationNotPermitted("Driver : " + username + " is not verified. Unable to take rides");
        }
        driver.setEligibleForRides(isReady);
        return driver;
    }

    @Override
    public void deleteDriver(long id) {
        Driver driver = driverRepository.findById(id).orElse(null);
        if(driver != null){
            driverRepository.delete(driver);
        }
    }

    @Override
    public Driver getDriverById(long id) {
        Optional<Driver> result = driverRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            return null;
        }
    }

    @Override
    public Driver getDriverByUsername(String username) {
        Optional<Driver> result = driverRepository.findByUsername(username);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new DataNotFoundException("No driver exists with username : " + username);
        }
    }

}
