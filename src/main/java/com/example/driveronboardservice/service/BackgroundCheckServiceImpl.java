package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.BackgroundCheck;
import com.example.driveronboardservice.model.Driver;
import com.example.driveronboardservice.repository.BackgroundCheckRepository;
import com.example.driveronboardservice.repository.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class BackgroundCheckServiceImpl implements BackgroundCheckService {

    private final BackgroundCheckRepository backgroundCheckRepository;

    public BackgroundCheckServiceImpl(BackgroundCheckRepository backgroundCheckRepository) {
        super();
        this.backgroundCheckRepository = backgroundCheckRepository;
    }

    @Override
    public BackgroundCheck initiateCheck(BackgroundCheck backgroundCheck) {
        return backgroundCheckRepository.save(backgroundCheck);
    }

    @Override
    public BackgroundCheck markVerified(long id) {
        BackgroundCheck backgroundCheck = backgroundCheckRepository.findById(id).orElse(null);
        if(backgroundCheck != null){
            backgroundCheck.setStatus("verified");
            return backgroundCheckRepository.save(backgroundCheck);
        }
        return null;
    }

    @Override
    public BackgroundCheck getById(long id) {
        return backgroundCheckRepository.findById(id).orElse(null);
    }
}
