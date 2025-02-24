package com.mtr.mtr_station.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TrainScheduleDto {
    private int status;
    private String message;
    @JsonProperty("sys_time")
    private String sysTime;
    @JsonProperty("curr_time")
    private String currTime;
    private Data data;
    private String isdelay;


    @Setter
    @Getter
    public class Data {
        // private Map<String, StationSchedule> stations;
        private StationSchedule stations;

    }

    @Setter
    @Getter
    public class StationSchedule {
        @JsonProperty("curr_time")
        private String currTime;
        @JsonProperty("sys_time")
        private String sysTime;
        @JsonProperty("DOWN")
        private List<TrainInfo> down;
        @JsonProperty("UP")
        private List<TrainInfo> up;
    }

    @Setter
    @Getter
    public class TrainInfo {
        private String seq;
        private String dest;
        private String plat;
        private String time;
        private String ttnt;
        private String valid;
        private String source;
}
}