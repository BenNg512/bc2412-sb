package com.mtr.mtr_station.dto;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainScheduleDto {
    @JsonProperty("status")
    private int status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("sys_time")
    private String sysTime;
    @JsonProperty("curr_time")
    private String currTime;
    @JsonProperty("data")
    private Map<String, StationScheduleDto> data;
    @JsonProperty("isdelay")
    private String isDelay;
}