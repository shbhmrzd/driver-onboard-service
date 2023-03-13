package com.example.driveronboardservice.controller;

import com.example.driveronboardservice.model.Driver;
import com.example.driveronboardservice.model.DriverSignupRequest;
import com.example.driveronboardservice.model.DriverResponse;
import com.example.driveronboardservice.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@Api(value = "Driver Management System", description = "Operations pertaining to driver in Driver Management System")
public class DriverController {
    @Autowired
    private ModelMapper modelMapper;

    private DriverService driverService;

    public DriverController(DriverService driverService) {
        super();
        this.driverService = driverService;
    }

    @PostMapping("/signup")
    @ApiOperation(value = "Sign up as a new driver")
    public ResponseEntity<DriverResponse> signUp(@RequestBody DriverSignupRequest driverRequest) {

        // convert DTO to entity
        Driver driver = modelMapper.map(driverRequest, Driver.class);

        Driver driverCreated = driverService.createDriver(driver);

        // convert entity to DTO
        DriverResponse driverResponse = modelMapper.map(driverCreated, DriverResponse.class);

        return new ResponseEntity<DriverResponse>(driverResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a driver by ID")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable(value = "id") Long driverId) {

        Driver driver = driverService.getDriverById(driverId);
        if(driver != null){
            DriverResponse driverResponse = modelMapper.map(driver, DriverResponse.class);
            return ResponseEntity.ok().body(driverResponse);
        }else{
            return new ResponseEntity<DriverResponse>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a driver by ID")
    public ResponseEntity<DriverResponse> getDriverById(@RequestBody Driver  driver, @PathVariable(value = "id") Long driverId) {
        if(driver.getId() != driverId){
            return new ResponseEntity<DriverResponse>(HttpStatus.BAD_REQUEST);
        }
        Driver driverUpdated = driverService.updateDriver(driverId, driver);
        if(driverUpdated != null){
            DriverResponse driverResponse = modelMapper.map(driverUpdated, DriverResponse.class);
            return ResponseEntity.ok().body(driverResponse);
        }else{
            return new ResponseEntity<DriverResponse>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hello")
    @ApiOperation(value = "Get a simple hello")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok().body("driver");
    }

}
