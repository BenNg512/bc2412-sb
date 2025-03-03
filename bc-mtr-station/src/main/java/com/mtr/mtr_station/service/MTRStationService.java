package com.mtr.mtr_station.service;

import java.util.List;
import com.mtr.mtr_station.entity.StationEntity;
import com.mtr.mtr_station.model.TrainScheduleDto;

public interface MTRStationService {
  public TrainScheduleDto getTrainSchedule(String line, String station);
  public List<TrainScheduleDto> getAllTrainSchedule();
  public StationEntity addStation(StationEntity stationEntity);
  public List<StationEntity> addStations(List<StationEntity> stationEntities);
  public void deleteStation(String stationCode);
}
