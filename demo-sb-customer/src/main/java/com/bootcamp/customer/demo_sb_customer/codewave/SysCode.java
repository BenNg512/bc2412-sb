package com.bootcamp.customer.demo_sb_customer.codewave;

public enum SysCode {
  OK("000000", "Success."),
  ID_NOT_FOUND("900001", "Id not found."),
  ID_REQUIRED("900002", "Id is required."),
  NAME_REQUIRED("900003", "Name is required."),
  RTE_NPE("999999", "Null pointer exception.");
  ;

  private String code;
  private String message;

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

