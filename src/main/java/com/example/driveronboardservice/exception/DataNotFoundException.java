package com.example.driveronboardservice.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DataNotFoundException extends RuntimeException {
    private String responseMessage;
}
