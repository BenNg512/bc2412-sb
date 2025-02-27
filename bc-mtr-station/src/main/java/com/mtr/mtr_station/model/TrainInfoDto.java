package com.mtr.mtr_station.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainInfoDto {
    private String ttnt;
    private String valid;
    private String plat;
    private String time;
    private String source;
    private String dest;
    private String seq;
}
