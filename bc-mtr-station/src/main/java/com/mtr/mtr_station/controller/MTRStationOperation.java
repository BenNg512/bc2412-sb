package com.mtr.mtr_station.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mtr.mtr_station.dto.TrainScheduleDto;

@RequestMapping("/mtr")
public interface MTRStationOperation {
  @GetMapping(value = "/stations")
  TrainScheduleDto getTainSchedule(@RequestParam String Line, @RequestParam String Station);
  
}
