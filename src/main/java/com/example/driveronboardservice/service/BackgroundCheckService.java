package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.BackgroundCheck;

public interface BackgroundCheckService {

    BackgroundCheck initiateCheck(BackgroundCheck backgroundCheck);

    BackgroundCheck markVerified(long id);

    BackgroundCheck getById(long id);
}
