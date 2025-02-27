package com.mtr.mtr_station.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mtr.mtr_station.codewave.ApiResp;
import com.mtr.mtr_station.entity.StationEntity;
import com.mtr.mtr_station.model.TrainScheduleDto;

@RequestMapping("/mtr")
public interface StationOperation {

  // http://localhost:8006/mtr/stations?line=KTL&station=KWT
  @GetMapping(value = "/stations")
  ApiResp<TrainScheduleDto> getTrainSchedule(@RequestParam String line, @RequestParam String station);
  
  // http://localhost:8006/mtr/stations
  // body input: please refer to column H in this excel:
  // https://docs.google.com/spreadsheets/d/1tiqdgLmqbw0eDMs-P2hHyUhCWFktK7ySFcXN5oMiPg4/edit?gid=0#gid=0
  @PostMapping(value = "/stations")
  List<StationEntity> addStations(@RequestBody List<StationEntity> stationEntity);
}
