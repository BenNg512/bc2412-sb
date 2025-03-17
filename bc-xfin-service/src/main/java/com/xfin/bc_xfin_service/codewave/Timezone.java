package com.xfin.bc_xfin_service.codewave;

public enum Timezone {
  UTC("UTC"),
  PST("America/Los_Angeles"),
  EST("America/New_York"),
  CET("Europe/Paris"),
  IST("Asia/Kolkata"),
  HKT("Asia/Hong_Kong"),
  JST("Asia/Tokyo")
  ;

  public String value;

  Timezone(String value) { 
    this.value = value; 
  }
} 

