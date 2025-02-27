package com.mtr.mtr_station.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mtr.mtr_station.codewave.ApiResp;
import com.mtr.mtr_station.codewave.SysCode;
import com.mtr.mtr_station.controller.StationOperation;
import com.mtr.mtr_station.entity.StationEntity;
import com.mtr.mtr_station.model.TrainScheduleDto;
import com.mtr.mtr_station.service.StationService;

@RestController
public class StationController implements StationOperation {
  @Autowired
  StationService stationService;

  public ApiResp<TrainScheduleDto> getTrainSchedule(String line, String station) {
    return ApiResp.<TrainScheduleDto> builder()
        .syscode(SysCode.OK)
        .data(this.stationService.getTrainSchedule(line, station))
        .build();
  }

  public List<StationEntity> addStations(List<StationEntity> stationEntity){
    return this.stationService.addStations(stationEntity);
  }

  
}
