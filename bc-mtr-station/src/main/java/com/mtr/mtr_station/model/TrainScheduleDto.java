package com.mtr.mtr_station.model;

import java.util.Map;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrainScheduleDto {
    private int status;
    private String message;
    private String sysTime;
    private String currTime;
    private Map<String, StationScheduleDto> data;
    private String isDelay;

    
}