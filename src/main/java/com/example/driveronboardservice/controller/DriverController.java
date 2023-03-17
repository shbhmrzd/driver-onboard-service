package com.example.driveronboardservice.controller;

import com.example.driveronboardservice.model.Driver;
import com.example.driveronboardservice.model.DriverSignupRequest;
import com.example.driveronboardservice.model.DriverResponse;
import com.example.driveronboardservice.model.User;
import com.example.driveronboardservice.service.DriverService;
import com.example.driveronboardservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Driver Management System", description = "Operations pertaining to driver in Driver Management System")
public class DriverController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private DriverService driverService;
    private UserService userService;

    public DriverController(DriverService driverService, UserService userService) {
        super();
        this.driverService = driverService;
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/signup")
    @ApiOperation(value = "Sign up as a new driver")
    public ResponseEntity signUp(@RequestBody DriverSignupRequest driverRequest) {

        User user = new User();
        user.setUsername(driverRequest.getUsername());
        user.setPassword(passwordEncoder.encode(driverRequest.getPassword()));
        user.setActive(true);

        User userCreated = userService.createUser(user);
        Driver driver = modelMapper.map(driverRequest, Driver.class);
        driver.setId(userCreated.getId());

        Driver driverCreated = driverService.createDriver(driver);

        DriverResponse driverResponse = modelMapper.map(driverCreated, DriverResponse.class);

        return new ResponseEntity<DriverResponse>(driverResponse, HttpStatus.CREATED);

    }

    @GetMapping("/driver/{username}")
    @ApiOperation(value = "Get a driver by username")
    public ResponseEntity<DriverResponse> getDriverByUsername(@PathVariable(value = "username") String username) {

        Driver driver = driverService.getDriverByUsername(username);
        DriverResponse driverResponse = modelMapper.map(driver, DriverResponse.class);
        if(driver.getLicense()!=null){
            driverResponse.setDocumentId(driver.getLicense().getId());
        }
        if(driver.getBackgroundCheck()!=null){
            driverResponse.setBackgroundCheckStatus(driver.getBackgroundCheck().getStatus());
        }

        return new ResponseEntity<DriverResponse>(driverResponse, HttpStatus.OK);
    }

    @PutMapping("/driver/{username}")
    @ApiOperation(value = "Update a driver by username")
    public ResponseEntity<DriverResponse> updateDriverByUsername(@RequestBody DriverSignupRequest  driverSignupRequest, @PathVariable(value = "username") String username) {
        if(!driverSignupRequest.getUsername().equals(username)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Driver driver = modelMapper.map(driverSignupRequest, Driver.class);
        Driver driverUpdated = driverService.updateDriver(username, driver);
        DriverResponse driverResponse = modelMapper.map(driverUpdated, DriverResponse.class);
        return ResponseEntity.ok().body(driverResponse);
    }

    @PatchMapping("/driver/{username}")
    @ApiOperation(value = "Update driver ready to take rides")
    public ResponseEntity<DriverResponse> updateDriverReadyForRides(@RequestParam(value = "isReady") Boolean isReady, @PathVariable(value = "username") String username) {

        Driver driverUpdated = driverService.updateIsReadyForRides(username, isReady);
        DriverResponse driverResponse = modelMapper.map(driverUpdated, DriverResponse.class);
        return ResponseEntity.ok().body(driverResponse);
    }


}
