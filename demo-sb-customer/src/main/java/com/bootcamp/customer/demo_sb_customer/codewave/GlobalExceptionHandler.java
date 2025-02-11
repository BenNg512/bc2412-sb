package com.bootcamp.customer.demo_sb_customer.codewave;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler extends RuntimeException{
  @ExceptionHandler(value = {BusinessException.class})
  public ApiResp<Void> handBusinessException(BusinessException e) {
    return ApiResp.<Void> builder() //
        .sysCode(e.getSysCode()) //
        .build();
  }

  @ExceptionHandler(NullPointerException.class)
  public ApiResp<Void> handNullPointerException(NullPointerException e) {
    return ApiResp.<Void> builder() //
        .sysCode(SysCode.RTE_NPE) //
        .build();
  }

  
}
