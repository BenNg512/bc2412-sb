package com.bootcamp.customer.demo_sb_customer.codewave;

// don't use lombok (need maven to install), it's a library
public class ApiResp<T> {
  private T data;
  private String message;
  private String code;

  public static<T> Builder<T> builder(){
    return new Builder<>();
  }

  // constructor here and give it back at ApiResponse<T> build()
  public ApiResp(Builder<T> builder) {
    this.data = builder.data;
    this.message = builder.message;
    this.code = builder.code;
  }

  public T getData() {
    return this.data;
  }
  public String getMessage() {
    return this.message;
  }
  public String getCode() {
    return this.code;
  }


  public static class Builder<T>{
    private T data;
    private String message;
    private String code;

    public Builder<T> sysCode(SysCode sysCode) {
      this.code = sysCode.getCode();
      this.message = sysCode.getMessage();
      return this;
    }

    // public Builder<T> code(String code) {
    //  this.code = code;
    //  return this;
    //}
    // public Builder<T> message(String message) {
    //  this.message = message;
    //  return this;
    //}

    public Builder<T> data(T data) {
      this.data = data;
      return this;
    }

    public ApiResp<T> build() {
      return new ApiResp<>(this);
    }
  
  }
}
