package com.eduhub.eduhub_backend.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

//This allows to handle exceptions globally across all controllers

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                  HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),
                                        "Not Found",ex.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalAccessError.class)
    public ResponseEntity<ErrorResponse> handleIllegalAccessError(IllegalAccessError ex,
        HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),"Illegal Arugement",ex.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex,HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.METHOD_NOT_ALLOWED.value(),"Exception",ex.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(errorResponse,HttpStatus.METHOD_NOT_ALLOWED);
    }




}
