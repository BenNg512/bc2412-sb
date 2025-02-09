package com.bootcamp.calculator.bc_calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.bootcamp.calculator.bc_calculator.model.ErrorResult;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  @ExceptionHandler(value = ArithmeticException.class)
  public ErrorResult handleArithmetic(RuntimeException e) {
    return new ErrorResult("Cannot divide by zero");
  }
    // invalid x, y, operation input throws IllegalArgumentException
  @ExceptionHandler(value = {IllegalArgumentException.class, NumberFormatException.class}) 
  public ErrorResult handleIllegal(RuntimeException e) {
    return new ErrorResult("Invalid input");
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<String> handleException(Exception ex, WebRequest request) {
    return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}

