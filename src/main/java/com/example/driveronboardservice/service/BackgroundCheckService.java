package com.example.driveronboardservice.service;

import com.example.driveronboardservice.model.BackgroundCheck;

public interface BackgroundCheckService {

    BackgroundCheck initiateCheck(BackgroundCheck backgroundCheck);

    BackgroundCheck markStatus(long id, String status, String comment);

    BackgroundCheck getById(long id);
}
