package com.example.driveronboardservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpException extends RuntimeException{
    private String responseMessage;
}

