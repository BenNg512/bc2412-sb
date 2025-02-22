package com.bootcamp.sbex2.bc_forum.codewave;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
  @ExceptionHandler(BusinessException.class)
  public ApiResp<Void> handleBusinessException(BusinessException e) {
    return ApiResp.<Void>builder() //
        .syscode(e.getSysCode()) //
        .build();
  }
  
}
