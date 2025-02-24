package com.mtr.mtr_station.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mtr.mtr_station.codewave.MTRStationCode;
import com.mtr.mtr_station.dto.TrainScheduleDto;
import com.mtr.mtr_station.service.MTRStationService;

@Service
public class MTRStationServiceImpl implements MTRStationService {
  @Autowired 
  RestTemplate restTemplate;

  public TrainScheduleDto getTrainSchedule(MTRStationCode mtrStation) {
      String url = "https://rt.data.gov.hk/v1/transport/mtr/getSchedule.php" +
              "?line=" + mtrStation.getLine() +
              "&sta=" + mtrStation.getStation();

      TrainScheduleDto schedule = this.restTemplate.getForObject(url, TrainScheduleDto.class);
      return schedule;
  }


  
}
