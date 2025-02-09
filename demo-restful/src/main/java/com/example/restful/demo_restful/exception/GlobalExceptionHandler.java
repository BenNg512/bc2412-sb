package com.example.restful.demo_restful.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.restful.demo_restful.model.ErrorResult;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler (value = ArithmeticException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorResult handleArithmeticException(ArithmeticException e) {
    return new ErrorResult(e.getMessage());
  }
}
