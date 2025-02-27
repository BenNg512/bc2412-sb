package com.mtr.mtr_station.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StationScheduleDto {
    private String currTime;
    private String sysTime;
    @JsonProperty("UP")
    private List<TrainInfoDto> up;
    @JsonProperty("DOWN")
    private List<TrainInfoDto> down;
}
