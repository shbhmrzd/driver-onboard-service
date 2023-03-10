package com.example.driveronboardservice.controller;

import com.example.driveronboardservice.model.Driver;
import com.example.driveronboardservice.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@Api(value = "Driver Management System", description = "Operations pertaining to driver in Driver Management System")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/signup")
    @ApiOperation(value = "Sign up as a new driver")
    public ResponseEntity<Driver> signUp(@RequestBody Driver driver) {
        Driver newDriver = driverService.signUp(driver);
        return ResponseEntity.ok().body(newDriver);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a driver by ID")
    public ResponseEntity<Driver> getDriverById(@PathVariable(value = "id") Long driverId) {
        Driver driver = driverService.getDriverById(driverId);
        return ResponseEntity.ok().body(driver);
    }

    // other controller methods...
}
