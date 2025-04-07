package com.mtr.mtr_station.service;

import com.mtr.mtr_station.model.TrainScheduleDto;

public interface MTRStationService {
  public TrainScheduleDto getTrainScheduleJson(String line, String station);
}
