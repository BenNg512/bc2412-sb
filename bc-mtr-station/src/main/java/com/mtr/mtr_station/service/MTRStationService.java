package com.mtr.mtr_station.service;

import com.mtr.mtr_station.dto.TrainScheduleDto;

public interface MTRStationService {
  public TrainScheduleDto getTrainSchedule(String line, String station);
}
