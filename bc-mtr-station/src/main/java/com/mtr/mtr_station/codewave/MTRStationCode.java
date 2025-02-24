package com.mtr.mtr_station.codewave;

public enum MTRStationCode {
  AEL_HOK("AEL","HOK"),
  AEL_KOW("AEL","KOW"),
  AEL_TSY("AEL","TSY"),
  AEL_AIR("AIR","AIR"),
  AEL_WAE("AIR","WAE"),
  TCL_HOK("BLK","HOK"),
  ;

  private String line;
  private String station;

  MTRStationCode(String line, String station) {
    this.line = line;
    this.station = station;
  }

  public String getLine() {
    return this.line;
  }

  public String getStation() {
    return this.station;
  }


}
