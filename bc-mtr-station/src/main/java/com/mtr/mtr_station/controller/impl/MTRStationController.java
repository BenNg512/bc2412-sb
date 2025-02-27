package com.mtr.mtr_station.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mtr.mtr_station.codewave.ApiResp;
import com.mtr.mtr_station.codewave.SysCode;
import com.mtr.mtr_station.controller.MTRStationOperation;
import com.mtr.mtr_station.model.TrainScheduleDto;
import com.mtr.mtr_station.service.MTRStationService;

@RestController
public class MTRStationController implements MTRStationOperation {
  @Autowired
  MTRStationService mtrStationService;

  public ApiResp<TrainScheduleDto> getTrainSchedule(String line, String station) {
    return ApiResp.<TrainScheduleDto> builder()
        .syscode(SysCode.OK)
        .data(mtrStationService.getTrainSchedule(line, station))
        .build();
  }
  
}
