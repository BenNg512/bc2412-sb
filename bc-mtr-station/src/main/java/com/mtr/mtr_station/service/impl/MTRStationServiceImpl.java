package com.mtr.mtr_station.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.mtr.mtr_station.entity.StationEntity;
import com.mtr.mtr_station.model.TrainScheduleDto;
import com.mtr.mtr_station.repository.StationRepository;
import com.mtr.mtr_station.service.MTRStationService;

@Service
public class MTRStationServiceImpl implements MTRStationService {
  @Autowired 
  RestTemplate restTemplate;
  @Autowired
  StationRepository stationRepository;

  @Override
  public TrainScheduleDto getTrainSchedule(String line, String station) {
      String url = "https://rt.data.gov.hk/v1/transport/mtr/getSchedule.php" +
              "?line=" + line +
              "&sta=" + station;

      TrainScheduleDto schedule = this.restTemplate.getForObject(url, TrainScheduleDto.class);
      return schedule;
  }

  @Override
  public List<TrainScheduleDto> getAllTrainSchedule() {
    List<StationEntity> stations = this.stationRepository.findAll();
    List<TrainScheduleDto> schedules = new ArrayList<>();
    for (StationEntity station : stations) {
      
      String url = "https://rt.data.gov.hk/v1/transport/mtr/getSchedule.php" +
              "?line=" + station.getLine() +
              "&sta=" + station.getStation();
      TrainScheduleDto schedule =this.restTemplate.getForObject(url, TrainScheduleDto.class);
      
    }
    return schedules;
  }

  @Override
  public StationEntity addStation(StationEntity stationEntity) {
    StationEntity entity = StationEntity.builder()
      .line(stationEntity.getLine())
      .station(stationEntity.getStation())
      .stationCode(stationEntity.getLine() + "_" + stationEntity.getStation())
      .build();
    return this.stationRepository.save(entity);
  }
  @Override
  public List<StationEntity> addStations(List<StationEntity> stationEntities){
    return this.stationRepository.saveAll(stationEntities);
  }

  @Override
  public void deleteStation(String stationCode) {
    this.stationRepository.deleteByStationCode(stationCode);
  }


  
}
