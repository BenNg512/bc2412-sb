package com.mtr.mtr_station.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mtr.mtr_station.codewave.ApiResp;
import com.mtr.mtr_station.codewave.SysCode;
import com.mtr.mtr_station.controller.MTRStationOperation;
import com.mtr.mtr_station.entity.StationEntity;
import com.mtr.mtr_station.model.TrainScheduleDto;
import com.mtr.mtr_station.service.MTRStationService;
import java.util.List;

@RestController
public class MTRStationController implements MTRStationOperation {
  @Autowired
  MTRStationService mtrStationService;

  @Override
  public ApiResp<TrainScheduleDto> getTrainSchedule(String line, String station) {
    return ApiResp.<TrainScheduleDto> builder()
        .syscode(SysCode.OK)
        .data(mtrStationService.getTrainSchedule(line, station))
        .build();
  }

  @Override
  public ApiResp<List<TrainScheduleDto>> getAllTrainSchedule() {
    return ApiResp.<List<TrainScheduleDto>> builder()
        .syscode(SysCode.OK)
        .data(mtrStationService.getAllTrainSchedule())
        .build();
  }

  @Override
  public StationEntity addStation(StationEntity stationEntity){
    return this.mtrStationService.addStation(stationEntity);
  }
  @Override
  public List<StationEntity> addStation(List<StationEntity> stationEntities){
    return this.mtrStationService.addStations(stationEntities);
  }

  @Override
  public void deleteStation(String stationCode) {
    this.mtrStationService.deleteStation(stationCode);
  }
  
}
