package com.bootcamp.sbex2.bc_forum.codewave;

public enum SysCode {
  OK("000000", "Success."),
  USER_NOT_FOUND("1", "User not found."),
  INVALID_INPUT("2", "Invalid input."),
  REST_ERROR("3", "RestTemplate Error - JsonPlaceHolder"),
  
  // Pre-handled Runtime Exception
  RTE_NPE("999999", "Null Pointer Exception."),;

  private final String code;
  private final String message;
  private SysCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return this.code;
  }
  public String getMessage() {
    return this.message;
  }
}
