package com.mtr.mtr_station.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.mtr.mtr_station.controller.MTRStationOperation;
import com.mtr.mtr_station.dto.TrainScheduleDto;
import com.mtr.mtr_station.service.MTRStationService;

@RestController
public class MTRStationController implements MTRStationOperation {
  @Autowired
  MTRStationService mtrStationService;

  public TrainScheduleDto getTainSchedule(String Line, String Station){
    return mtrStationService.getTrainSchedule(Line, Station);
  }
  
}
