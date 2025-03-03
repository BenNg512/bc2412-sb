package com.mtr.mtr_station.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mtr.mtr_station.codewave.ApiResp;
import com.mtr.mtr_station.entity.StationEntity;
import com.mtr.mtr_station.model.TrainScheduleDto;
import java.util.List;

@RequestMapping("/mtr")
public interface MTRStationOperation {

  // http://localhost:8006/mtr/stations?line=KTL&station=KWT
  @GetMapping(value = "/stations")
  ApiResp<TrainScheduleDto> getTrainSchedule(@RequestParam String line, @RequestParam String station);

  // http://localhost:8006/mtr/schedules
  // DDOS -> need to get 100+ API data at once
  @GetMapping(value = "/schedules")
  ApiResp<List<TrainScheduleDto>> getAllTrainSchedule();
  
  // http://localhost:8006/mtr/station
  @PostMapping(value = "/station")
  StationEntity addStation(@RequestBody StationEntity stationEntity);

  // http://localhost:8006/mtr/stations
  @PostMapping(value = "/stations")
  List<StationEntity> addStation(@RequestBody List<StationEntity> stationEntity);

  // http://localhost:8006/mtr/station?stationCode=KWT
  @DeleteMapping(value = "/station")
  public void deleteStation(@RequestParam String stationCode);
}
