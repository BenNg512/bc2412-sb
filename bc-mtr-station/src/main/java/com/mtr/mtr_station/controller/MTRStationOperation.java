package com.mtr.mtr_station.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mtr.mtr_station.codewave.ApiResp;
import com.mtr.mtr_station.codewave.MTRStationCode;
import com.mtr.mtr_station.dto.TrainScheduleDto;

@RequestMapping("/mtr")
public interface MTRStationOperation {

  // http://localhost:8006/mtr/stations?code=AEL_HOK
  @GetMapping(value = "/stations")
  ApiResp<TrainScheduleDto> getTainSchedule(@RequestParam(value = "code") MTRStationCode mtrStationCode);
  
}
