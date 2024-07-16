package com.court.eateasy.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException e) {
      //  return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
