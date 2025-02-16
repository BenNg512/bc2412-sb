package com.bootcamp.sbex2.bc_forum.codewave;

public class RestTemplateException extends RuntimeException {
  private SysCode syscode;

  public static RestTemplateException of(SysCode sysCode) {
    return new RestTemplateException(sysCode);
  }

  private RestTemplateException(SysCode sysCode) {
    super(sysCode.getMessage());
    this.syscode = sysCode;
  }

  public SysCode getSysCode() {
    return this.syscode;
  }
}
