package com.mtr.mtr_station.codewave;

public enum SysCode {
  OK("000000", "Success."),
  INVALID_INPUT("2", "Invalid input."),
  REST_ERROR("3", "RestTemplate Error - JsonPlaceHolder"),
  API_UNAVAILABLE("999998", "Json PlaceHolder API Unavailable."),
  DATABASE_CONNECTION_ERROR("999999", "Database Connection Failed."),
  RTE_NPE("999999", "Null Pointer Exception."), 
  ;

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
