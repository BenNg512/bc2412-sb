package com.bootcamp.calculator.bc_calculator.model;

public class ErrorResult {
  private String message;
  
  public ErrorResult(String message) {
    this.message = message;
  }
  public String getMessage() {
    return this.message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

}
