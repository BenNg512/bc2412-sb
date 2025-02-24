package com.mtr.mtr_station.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mtr.mtr_station.codewave.ApiResp;
import com.mtr.mtr_station.codewave.MTRStationCode;
import com.mtr.mtr_station.codewave.SysCode;
import com.mtr.mtr_station.controller.MTRStationOperation;
import com.mtr.mtr_station.dto.TrainScheduleDto;
import com.mtr.mtr_station.service.MTRStationService;

@RestController
public class MTRStationController implements MTRStationOperation {
  @Autowired
  MTRStationService mtrStationService;

  public ApiResp<TrainScheduleDto> getTainSchedule(MTRStationCode mtrStationCode) {
    try {mtrStationService.getTrainSchedule(mtrStationCode);}
    catch (NullPointerException e) {
      return null;
    }
    return ApiResp.<TrainScheduleDto> builder()
        .syscode(SysCode.OK)
        .data(mtrStationService.getTrainSchedule(mtrStationCode))
        .build();
  }
  
}
