package com.mtr.mtr_station.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.mtr.mtr_station.codewave.MTRLine;
import com.mtr.mtr_station.codewave.MTRStation;
import com.mtr.mtr_station.dto.TrainDataDto;
import com.mtr.mtr_station.dto.map.DtoMapper;
import com.mtr.mtr_station.model.TrainInfoDto;
import com.mtr.mtr_station.model.TrainScheduleDto;
import com.mtr.mtr_station.service.MTRStationService;

@Controller
public class MTRStationController {

  @Autowired
  MTRStationService mtrStationService;

  // http://localhost:8006/hk-mtr/KTL/KWT
  @GetMapping(value = "/hk-mtr/{line}/{station}")
  public String getTrainSchedule(Model model, @PathVariable String line, @PathVariable String station){
    model.addAttribute("line", line);
    model.addAttribute("station", station);

    MTRLine mtrLine = MTRLine.fromString(line);
    model.addAttribute("lineName", mtrLine.getNameEN());

    try{
      MTRStation mtrStation = MTRStation.fromString(line + "_" + station);
      model.addAttribute("stationName", mtrStation.getNameEN());
    } catch (Exception e) {
      String firstStation = MTRStation.getStartMtrStation(line);
      return "redirect:/hk-mtr/" + line + "/" + firstStation;
    }
    
    MTRStation mtrStation = MTRStation.fromString(line + "_" + station);
    model.addAttribute("stationName", mtrStation.getNameEN());

    TrainScheduleDto schedule = mtrStationService.getTrainScheduleJson(line, station);
    TrainDataDto trainData = DtoMapper.mapToTrainDataDto(schedule, line, station);

    model.addAttribute("currTime", schedule.getCurrTime());
    model.addAttribute("sysTime", schedule.getSysTime());
    model.addAttribute("isDelay", schedule.getIsDelay());

    List<TrainInfoDto> up = trainData.getUp();
    for (TrainInfoDto trainInfoDto : up) {
      trainInfoDto.setDest(MTRStation.fromString(line + "_" +trainInfoDto.getDest()).getNameEN());
    }
    model.addAttribute("up", up);

    List<TrainInfoDto> down = trainData.getDown();
    for (TrainInfoDto trainInfoDto : down) {
      trainInfoDto.setDest(MTRStation.fromString(line + "_" +trainInfoDto.getDest()).getNameEN());
    }
    model.addAttribute("down", down);

    String isDelay = schedule.getIsDelay();
    model.addAttribute("isDelay", isDelay);

    model.addAttribute("lines", MTRLine.values()); // Pass all enum values
    model.addAttribute("selectedLine", line); // Optional: Pre-select a value

    List<MTRStation> stations = List.of(MTRStation.values())
                                .stream()
                                .filter(e -> e.getLine().equalsIgnoreCase(line))
                                .collect(Collectors.toList());
    model.addAttribute("stations", stations); // Pass all enum values
    model.addAttribute("selectedStation", station); // Optional: Pre-select a value

  return "trainSchedule";
  }
}
