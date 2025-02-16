package com.bootcamp.sbex2.bc_forum.codewave;

public class UserNotFoundException extends RuntimeException {
  private SysCode syscode;
  public static UserNotFoundException of(SysCode sysCode) {
    return new UserNotFoundException(sysCode);
  }

  private UserNotFoundException(SysCode sysCode) {
    super(sysCode.getMessage());
    this.syscode = sysCode;
  }

  public SysCode getSysCode() {
    return this.syscode;
  }

}
