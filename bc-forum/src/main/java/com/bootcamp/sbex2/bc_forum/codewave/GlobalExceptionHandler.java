package com.bootcamp.sbex2.bc_forum.codewave;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public ApiResp<Void> handleBusinessException(BusinessException e) {
    return ApiResp.<Void>builder() //
        .syscode(e.getSysCode()) //
        .build();
  }

  @ExceptionHandler(NullPointerException.class)
  public ApiResp<Void> handleNullPointerException() {
    return ApiResp.<Void>builder() //
        .syscode(SysCode.RTE_NPE) //
        .build();
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ApiResp<Void> handleUserNotFoundException(UserNotFoundException ex) {
    return ApiResp.<Void>builder()
        .syscode(SysCode.USER_NOT_FOUND)
        .build();
  }

  @ExceptionHandler(RestTemplateException.class)
  public ApiResp<String> handleRestTemplateException(RestTemplateException ex) {
    return ApiResp.<String>builder()
        .syscode(SysCode.REST_ERROR)
        .data(ex.getMessage())
        .build();
  }
}
