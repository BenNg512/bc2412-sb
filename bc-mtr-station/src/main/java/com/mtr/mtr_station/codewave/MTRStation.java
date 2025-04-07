package com.mtr.mtr_station.codewave;

import java.util.Arrays;

public enum MTRStation {
  AEL_HOK("AEL","HOK","香港","Hong Kong"),
  AEL_KOW("AEL","KOW","九龍","Kowloon"),
  AEL_TSY("AEL","TSY","青衣","Tsing Yi"),
  AEL_AIR("AEL","AIR","機場","Airport"),
  AEL_AWE("AEL","AWE","博覽館","AsiaWorld-Expo"),
  DRL_DIS("DRL","DIS","迪士尼","Disneyland Resort"),
  DRL_SUN("DRL","SUN","欣澳","Sunny Bay"),
  EAL_LMC("EAL","LMC","落馬洲","Lok Ma Chau"),
  EAL_ADM("EAL","ADM","金鐘","Admiralty"),
  EAL_EXC("EAL","EXC","會展","Exhibition Centre"),
  EAL_HUH("EAL","HUH","紅磡","Hung Hom"),
  EAL_MKK("EAL","MKK","旺角東","Mong Kok East"),
  EAL_KOT("EAL","KOT","九龍塘","Kowloon Tong"),
  EAL_TAW("EAL","TAW","大圍","Tai Wai"),
  EAL_SHT("EAL","SHT","沙田","Sha Tin"),
  EAL_FOT("EAL","FOT","火炭","Fo Tan"),
  EAL_UNI("EAL","UNI","大學","University"),
  EAL_TAP("EAL","TAP","大埔墟","Tai Po Market"),
  EAL_TWO("EAL","TWO","太和","Tai Wo"),
  EAL_FAN("EAL","FAN","粉嶺","Fanling"),
  EAL_SHS("EAL","SHS","上水","Sheung Shui"),
  EAL_LOW("EAL","LOW","羅湖","Lo Wu"),
  ISL_KET("ISL","KET","堅尼地城","Kennedy Town"),
  ISL_HKU("ISL","HKU","香港大學","HKU"),
  ISL_SYP("ISL","SYP","西營盤","Sai Ying Pun"),
  ISL_SHW("ISL","SHW","上環","Sheung Wan"),
  ISL_CEN("ISL","CEN","中環","Central"),
  ISL_ADM("ISL","ADM","金鐘","Admiralty"),
  ISL_WAC("ISL","WAC","灣仔","Wan Chai"),
  ISL_CAB("ISL","CAB","銅鑼灣","Causeway Bay"),
  ISL_TIH("ISL","TIH","天后","Tin Hau"),
  ISL_FOH("ISL","FOH","炮台山","Fortress Hill"),
  ISL_NOP("ISL","NOP","北角","North Point"),
  ISL_QUB("ISL","QUB","鰂魚涌","Quarry Bay"),
  ISL_TAK("ISL","TAK","太古","Tai Koo"),
  ISL_SWH("ISL","SWH","西灣河","Sai Wan Ho"),
  ISL_SKW("ISL","SKW","筲箕灣","Shau Kei Wan"),
  ISL_HFC("ISL","HFC","杏花邨","Heng Fa Chuen"),
  ISL_CHW("ISL","CHW","柴灣","Chai Wan"),
  KTL_WHA("KTL","WHA","黃埔","Whampoa"),
  KTL_HOM("KTL","HOM","何文田","Ho Man Tin"),
  KTL_YMT("KTL","YMT","油麻地","Yau Ma Tei"),
  KTL_MOK("KTL","MOK","旺角","Mong Kok"),
  KTL_PRE("KTL","PRE","太子","Prince Edward"),
  KTL_SKM("KTL","SKM","石硤尾","Shek Kip Mei"),
  KTL_KOT("KTL","KOT","九龍塘","Kowloon Tong"),
  KTL_LOF("KTL","LOF","樂富","Lok Fu"),
  KTL_WTS("KTL","WTS","黃大仙","Wong Tai Sin"),
  KTL_DIH("KTL","DIH","鑽石山","Diamond Hill"),
  KTL_CHH("KTL","CHH","彩虹","Choi Hung"),
  KTL_KOB("KTL","KOB","九龍灣","Kowloon Bay"),
  KTL_NTK("KTL","NTK","牛頭角","Ngau Tau Kok"),
  KTL_KWT("KTL","KWT","觀塘","Kwun Tong"),
  KTL_LAT("KTL","LAT","藍田","Lam Tin"),
  KTL_YAT("KTL","YAT","油塘","Yau Tong"),
  KTL_TIK("KTL","TIK","調景嶺","Tiu Keng Leng"),
  TML_TUM("TML","TUM","屯門","Tuen Mun"),
  TML_SIH("TML","SIH","兆康","Siu Hong"),
  TML_TIS("TML","TIS","天水圍","Tin Shui Wai"),
  TML_LOP("TML","LOP","朗屏","Long Ping"),
  TML_YUL("TML","YUL","元朗","Yuen Long"),
  TML_KSR("TML","KSR","錦上路","Kam Sheung Road"),
  TML_TWW("TML","TWW","荃灣西","Tsuen Wan West"),
  TML_MEF("TML","MEF","美孚","Mei Foo"),
  TML_NAC("TML","NAC","南昌","Nam Cheong"),
  TML_AUS("TML","AUS","柯士甸","Austin"),
  TML_ETS("TML","ETS","尖東","East Tsim Sha Tsui"),
  TML_HUH("TML","HUH","紅磡","Hung Hom"),
  TML_HOM("TML","HOM","何文田","Ho Man Tin"),
  TML_TKW("TML","TKW","土瓜灣","To Kwa Wan"),
  TML_SUW("TML","SUW","宋皇臺","Sung Wong Toi"),
  TML_KAT("TML","KAT","啟德","Kai Tak"),
  TML_DIH("TML","DIH","鑽石山","Diamond Hill"),
  TML_HIK("TML","HIK","顯徑","Hin Keng"),
  TML_TAW("TML","TAW","大圍","Tai Wai"),
  TML_CKT("TML","CKT","車公廟","Che Kung Temple"),
  TML_STW("TML","STW","沙田圍","Sha Tin Wai"),
  TML_CIO("TML","CIO","第一城","City One"),
  TML_SHM("TML","SHM","石門","Shek Mun"),
  TML_TSH("TML","TSH","大水坑","Tai Shui Hang"),
  TML_HEO("TML","HEO","恆安","Heng On"),
  TML_MOS("TML","MOS","馬鞍山","Ma On Shan"),
  TML_WKS("TML","WKS","烏溪沙","Wu Kai Sha"),
  TCL_HOK("TCL","HOK","香港","Hong Kong"),
  TCL_KOW("TCL","KOW","九龍","Kowloon"),
  TCL_OLY("TCL","OLY","奧運","Olympic"),
  TCL_NAC("TCL","NAC","南昌","Nam Cheong"),
  TCL_LAK("TCL","LAK","茘景","Lai King"),
  TCL_TSY("TCL","TSY","青衣","Tsing Yi"),
  TCL_SUN("TCL","SUN","欣澳","Sunny Bay"),
  TCL_TUC("TCL","TUC","東涌","Tung Chung"),
  TKL_LHP("TKL","LHP","康城","LOHAS Park"),
  TKL_NOP("TKL","NOP","北角","North Point"),
  TKL_QUB("TKL","QUB","鰂魚涌","Quarry Bay"),
  TKL_YAT("TKL","YAT","油塘","Yau Tong"),
  TKL_TIK("TKL","TIK","調景嶺","Tiu Keng Leng"),
  TKL_TKO("TKL","TKO","將軍澳","Tseung Kwan O"),
  TKL_HAH("TKL","HAH","坑口","Hang Hau"),
  TKL_POA("TKL","POA","寶琳","Po Lam"),
  TWL_CEN("TWL","CEN","中環","Central"),
  TWL_ADM("TWL","ADM","金鐘","Admiralty"),
  TWL_TST("TWL","TST","尖沙咀","Tsim Sha Tsui"),
  TWL_JOR("TWL","JOR","佐敦","Jordan"),
  TWL_YMT("TWL","YMT","油麻地","Yau Ma Tei"),
  TWL_MOK("TWL","MOK","旺角","Mong Kok"),
  TWL_PRE("TWL","PRE","太子","Prince Edward"),
  TWL_SSP("TWL","SSP","深水埗","Sham Shui Po"),
  TWL_CSW("TWL","CSW","長沙灣","Cheung Sha Wan"),
  TWL_LCK("TWL","LCK","茘枝角","Lai Chi Kok"),
  TWL_MEF("TWL","MEF","美孚","Mei Foo"),
  TWL_LAK("TWL","LAK","茘景","Lai King"),
  TWL_KWF("TWL","KWF","葵芳","Kwai Fong"),
  TWL_KWH("TWL","KWH","葵興","Kwai Hing"),
  TWL_TWH("TWL","TWH","大窩口","Tai Wo Hau"),
  TWL_TSW("TWL","TSW","荃灣","Tsuen Wan"),
  SIL_SOH("SIL","SOH","海怡半島","South Horizons"),
  SIL_LET("SIL","LET","利東","Lei Tung"),
  SIL_WCH("SIL","WCH","黃竹坑","Wong Chuk Hang"),
  SIL_OCP("SIL","OCP","海洋公園","Ocean Park"),
  SIL_ADM("SIL","ADM","金鐘","Admiralty"),
  ;

  public final String line;
  public final String station;
  public final String nameCN;
  public final String nameEN;
  private MTRStation(String line, String station, String nameCN, String nameEN) {
    this.line = line;
    this.station = station;
    this.nameCN = nameCN;
    this.nameEN = nameEN;
  }
  public String getLine() {
    return this.line;
  }
  public String getStation() {
    return this.station;
  }
  public String getNameCN() {
    return this.nameCN;
  }
  public String getNameEN() {
    return this.nameEN;
  }
  public static MTRStation fromString(String input) {
    for (MTRStation location : MTRStation.values()) {
        if (location.name().equalsIgnoreCase(input)) {
            return location;
        }
    }
    throw new IllegalArgumentException("No enum found for input: " + input);
  }
  public static String getStartMtrStation(String line) {
    return Arrays.stream(MTRStation.values())
                .filter(mtrStation -> mtrStation.getLine().equalsIgnoreCase(line)) // Match the line
                .findFirst() // Get the first station
                .map(MTRStation::getStation) // Map to the station attribute
                .orElseThrow(() -> new IllegalArgumentException("No stations found for line: " + line));
    }

}
