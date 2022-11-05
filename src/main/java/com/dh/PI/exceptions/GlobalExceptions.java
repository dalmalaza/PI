package com.dh.PI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> tratamientoErrorResourceNotFound(ResourceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atencion: Error " + rnfe.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> tratamientoBadRequest(BadRequestException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());
    }
}
