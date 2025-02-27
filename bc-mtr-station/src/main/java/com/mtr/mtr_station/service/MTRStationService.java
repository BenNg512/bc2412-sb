package com.mtr.mtr_station.service;

import com.mtr.mtr_station.model.TrainScheduleDto;

public interface MTRStationService {
  public TrainScheduleDto getTrainSchedule(String line, String station);
}
