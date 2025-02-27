package com.mtr.mtr_station.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationScheduleDto {
    @JsonProperty("curr_time")
    private String currTime;
    @JsonProperty("sys_time")
    private String sysTime;
    @JsonProperty("UP")
    private List<TrainInfoDto> up;
    @JsonProperty("DOWN")
    private List<TrainInfoDto> down;
}
