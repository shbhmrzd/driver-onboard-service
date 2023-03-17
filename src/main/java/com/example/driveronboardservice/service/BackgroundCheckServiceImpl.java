package com.example.driveronboardservice.service;

import com.example.driveronboardservice.exception.DataNotFoundException;
import com.example.driveronboardservice.model.BackgroundCheck;
import com.example.driveronboardservice.repository.BackgroundCheckRepository;
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
    public BackgroundCheck markStatus(long id, String status, String comment) {
        BackgroundCheck backgroundCheck = backgroundCheckRepository.findById(id).orElse(null);
        if(backgroundCheck == null){
            throw new DataNotFoundException("No background check job found for id : " + id);
        }
        backgroundCheck.setStatus(status);
        backgroundCheck.setComments(comment);
        return backgroundCheckRepository.save(backgroundCheck);
    }

    @Override
    public BackgroundCheck getById(long id) {
        BackgroundCheck backgroundCheck =  backgroundCheckRepository.findById(id).orElse(null);
        if(backgroundCheck == null){
            throw new DataNotFoundException("No background check job found for id : " + id);
        }
            return backgroundCheck;
    }
}
