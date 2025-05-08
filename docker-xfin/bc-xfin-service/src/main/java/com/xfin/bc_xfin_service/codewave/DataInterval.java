package com.xfin.bc_xfin_service.codewave;

public enum DataInterval {
ONE_DAY("1d"),
FIVE_DAYS("5d"),
ONE_MONTH("1mo"),
THREE_MONTHS("3mo"),
SIX_MONTHS("6mo"),
;
  public String value;
  private DataInterval(String value){
    this.value = value;
  }
  
}
