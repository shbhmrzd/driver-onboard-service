package com.example.driveronboardservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OperationNotPermitted extends RuntimeException{
    private String responseMessage;
}
