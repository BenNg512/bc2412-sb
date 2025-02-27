package com.mtr.mtr_station.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mtr.mtr_station.codewave.MTRApi;
import com.mtr.mtr_station.entity.StationEntity;
import com.mtr.mtr_station.model.TrainScheduleDto;
import com.mtr.mtr_station.repository.StationRepository;
import com.mtr.mtr_station.service.StationService;

@Service
public class StationServiceImpl implements StationService {
  @Autowired 
  RestTemplate restTemplate;

  @Autowired
  StationRepository stationRepository;

  public TrainScheduleDto getTrainSchedule(String line, String station) {
      String url = MTRApi.getUrl(line, station);

      TrainScheduleDto schedule = this.restTemplate.getForObject(url, TrainScheduleDto.class);
      return schedule;
  }

  public List<StationEntity> addStations(List<StationEntity> stationEntities) {
    return this.stationRepository.saveAll(stationEntities);
  }

  
}
