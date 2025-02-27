package com.mtr.mtr_station.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mtr.mtr_station.codewave.ApiResp;
import com.mtr.mtr_station.model.TrainScheduleDto;

@RequestMapping("/mtr")
public interface MTRStationOperation {

  // http://localhost:8006/mtr/stations?line=KTL&station=KWT
  @GetMapping(value = "/stations")
  ApiResp<TrainScheduleDto> getTrainSchedule(@RequestParam String line, @RequestParam String station);
  
}
