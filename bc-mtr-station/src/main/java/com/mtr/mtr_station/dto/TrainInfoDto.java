package com.mtr.mtr_station.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainInfoDto {
    private String seq;
    private String dest;
    private String plat;
    private String time;
    private String ttnt;
    private String valid;
    private String source;
}
