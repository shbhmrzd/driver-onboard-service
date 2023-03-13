package com.example.driveronboardservice.controller;

import com.example.driveronboardservice.model.*;
import com.example.driveronboardservice.service.BackgroundCheckService;
import com.example.driveronboardservice.service.DriverService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/background_check")
public class BackgroundCheckController {

    @Autowired
    private ModelMapper modelMapper;

    private BackgroundCheckService backgroundCheckService;
    private DriverService driverService;

    public BackgroundCheckController(BackgroundCheckService backgroundCheckService, DriverService driverService) {
        super();
        this.backgroundCheckService = backgroundCheckService;
        this.driverService = driverService;
    }

    @PostMapping("/initiate")
    @ApiOperation(value = "Initiate background verification for driver")
    public ResponseEntity<BackgroundCheckDTO> initiateCheck(@RequestParam("driverId") Long driverId) {

        Driver driver = driverService.getDriverById(driverId);
        BackgroundCheck backgroundCheck = new BackgroundCheck();
        backgroundCheck.setDriver(driver);
        backgroundCheck.setStatus("initiated");



        BackgroundCheck backgroundCheckCreated = backgroundCheckService.initiateCheck(backgroundCheck);

        driver.setBackgroundCheck(backgroundCheckCreated);
        driverService.updateDriver(driverId, driver);

        // convert entity to DTO
        BackgroundCheckDTO backgroundCheckDTO = modelMapper.map(backgroundCheckCreated, BackgroundCheckDTO.class);

        return new ResponseEntity<BackgroundCheckDTO>(backgroundCheckDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get background check details by ID")
    public ResponseEntity<BackgroundCheckDTO> getBackgroundCheckById(@PathVariable(value = "id") Long id) {

        BackgroundCheck backgroundCheck = backgroundCheckService.getById(id);
        if(backgroundCheck != null){
            BackgroundCheckDTO backgroundCheckDTO = modelMapper.map(backgroundCheck, BackgroundCheckDTO.class);
            return ResponseEntity.ok().body(backgroundCheckDTO);
        }else{
            return new ResponseEntity<BackgroundCheckDTO>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}/verified")
    @ApiOperation(value = "Mark the background check as verified by ID")
    public ResponseEntity<BackgroundCheckDTO> markVerified( @PathVariable(value = "id") Long id) {

        BackgroundCheck backgroundCheck = backgroundCheckService.markVerified(id);
        if(backgroundCheck != null){
            BackgroundCheckDTO backgroundCheckDTO = modelMapper.map(backgroundCheck, BackgroundCheckDTO.class);
            return ResponseEntity.ok().body(backgroundCheckDTO);
        }else{
            return new ResponseEntity<BackgroundCheckDTO>(HttpStatus.NOT_FOUND);
        }
    }


}
