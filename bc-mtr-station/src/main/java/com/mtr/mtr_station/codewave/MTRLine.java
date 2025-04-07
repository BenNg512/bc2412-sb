package com.mtr.mtr_station.codewave;

public enum MTRLine {
  AEL("機場快綫","Airport Express"),
  DRL("迪士尼綫","Disneyland Resort Line"),
  EAL("東鐵綫","East Rail Line"),
  ISL("港島綫","Island Line"),
  KTL("觀塘綫","Kwun Tong Line"),
  TML("屯馬綫","Tuen Ma Line"),
  TCL("東涌綫","Tung Chung Line"),
  TKL("將軍澳綫","Tseung Kwan O Line"),
  TWL("荃灣綫","Tsuen Wan Line"),
  SIL("南港島綫","South Island Line"),
  ;
  
  private String nameCN;
  private String nameEN;
  
  MTRLine(String nameCN, String nameEN) {
    this.nameCN = nameCN;
    this.nameEN = nameEN;
  }
  public String getNameCN() {
    return this.nameCN;
  }
  public String getNameEN() {
    return this.nameEN;
  }
  public static MTRLine fromString(String input) {
    for (MTRLine line : MTRLine.values()) {
        if (line.name().equalsIgnoreCase(input)) {
            return line;
        }
    }
    throw new IllegalArgumentException("No enum found for input: " + input);
  }
}
