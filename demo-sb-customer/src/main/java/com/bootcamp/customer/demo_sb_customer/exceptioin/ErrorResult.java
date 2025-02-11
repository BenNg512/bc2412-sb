package com.bootcamp.customer.demo_sb_customer.exceptioin;

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
