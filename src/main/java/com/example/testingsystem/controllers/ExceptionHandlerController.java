package com.example.testingsystem.controllers;

import com.example.testingsystem.exceptions.DuplicateLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(DuplicateLoginException.class)
    public ResponseEntity<Object> handleDuplicateLoginException(DuplicateLoginException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
