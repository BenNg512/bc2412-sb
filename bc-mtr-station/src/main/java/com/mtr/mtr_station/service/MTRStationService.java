package com.mtr.mtr_station.service;

import com.mtr.mtr_station.codewave.MTRStationCode;
import com.mtr.mtr_station.dto.TrainScheduleDto;

public interface MTRStationService {
  public TrainScheduleDto getTrainSchedule(MTRStationCode mtrStation);
}
