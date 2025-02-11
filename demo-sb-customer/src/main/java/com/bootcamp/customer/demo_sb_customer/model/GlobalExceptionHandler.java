package com.bootcamp.customer.demo_sb_customer.model;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler extends RuntimeException{
  @ExceptionHandler(value = {BusinessException.class})
  public ErrorResult handleIllegal(RuntimeException e) {
    return new ErrorResult("Invalid input");
  }
  
}
