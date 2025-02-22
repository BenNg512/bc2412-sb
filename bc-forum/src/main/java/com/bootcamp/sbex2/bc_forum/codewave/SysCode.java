package com.bootcamp.sbex2.bc_forum.codewave;

public enum SysCode {
  OK("000000", "Success."),
  CREATED("000000", "Create Success."),
  USER_NOT_FOUND("1", "User Not Found."),
  POST_NOT_FOUND("100001","Post Not Found"), 
  COMMENT_NOT_FOUND("100002","Comment Not Found"), 
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
