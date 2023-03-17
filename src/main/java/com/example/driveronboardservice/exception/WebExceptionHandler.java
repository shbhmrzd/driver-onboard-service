package com.example.driveronboardservice.exception;

import com.example.driveronboardservice.error.ApiError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class WebExceptionHandler {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @ExceptionHandler(SignUpException.class)
    ResponseEntity<?> handleSignUpException(SignUpException ex) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        transactionManager.rollback(status);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,  ex.getResponseMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        transactionManager.rollback(status);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getCause().getCause().getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        transactionManager.rollback(status);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,  ex.getCause().getCause().getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    ResponseEntity<?> handleSQLException(SQLException ex) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        transactionManager.rollback(status);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getCause().getCause().getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    ResponseEntity<?> handleDataNotFoundException(DataNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getCause().getCause().getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationNotPermitted.class)
    ResponseEntity<?> handleOperationNotPermitted(OperationNotPermitted ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getResponseMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    ResponseEntity<?> handleIOException(IOException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, ex.getCause().getCause().getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
