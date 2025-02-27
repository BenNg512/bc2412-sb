package com.mtr.mtr_station.service;

import java.util.List;
import com.mtr.mtr_station.entity.StationEntity;
import com.mtr.mtr_station.model.TrainScheduleDto;

public interface StationService {
  public TrainScheduleDto getTrainSchedule(String line, String station);
  public List<StationEntity> addStations(List<StationEntity> stationEntities);
}
