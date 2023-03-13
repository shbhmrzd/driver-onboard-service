package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.Driver;
import com.example.driveronboardservice.repository.DriverRepository;
import org.springframework.stereotype.Service;

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
        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriver(long id, Driver driverRequest) {
        Driver driver = driverRepository.findById(id).orElse(null);
        if(driver != null){
            driver.setVehicleMake(driverRequest.getVehicleMake());
            driver.setVehicleModel(driverRequest.getVehicleModel());
            driver.setVehicleYear(driverRequest.getVehicleYear());
            return driverRepository.save(driver);
        }
        return null;
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
}
