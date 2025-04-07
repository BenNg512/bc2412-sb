package com.mtr.mtr_station.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mtr.mtr_station.codewave.ApiResp;
import com.mtr.mtr_station.codewave.SysCode;
import com.mtr.mtr_station.controller.MTRStationOperation;
import com.mtr.mtr_station.model.TrainScheduleDto;
import com.mtr.mtr_station.service.MTRStationService;

@RestController
public class MTRStationRestController implements MTRStationOperation {
  @Autowired
  MTRStationService mtrStationService;

  @Override
  public ApiResp<TrainScheduleDto> getTrainScheduleJson(String line, String station) {
    return ApiResp.<TrainScheduleDto> builder()
        .syscode(SysCode.OK)
        .data(mtrStationService.getTrainScheduleJson(line, station))
        .build();
  }

  @Override
  public TrainScheduleDto getTrainScheduleJson2(String line, String station) {
    return mtrStationService.getTrainScheduleJson(line, station);
  }
  
}
