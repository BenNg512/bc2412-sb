package com.mtr.mtr_station.dto.map;

import java.util.List;
import com.mtr.mtr_station.dto.TrainDataDto;
import com.mtr.mtr_station.model.StationScheduleDto;
import com.mtr.mtr_station.model.TrainInfoDto;
import com.mtr.mtr_station.model.TrainScheduleDto;

public class DtoMapper {
  public TrainDataDto map(TrainScheduleDto dto) {
    List<TrainInfoDto> up = ((TrainDataDto) dto.getData()).getUp();
    List<TrainInfoDto> down = ((TrainDataDto) dto.getData()).getDown();
    TrainDataDto data = new TrainDataDto();
    data.setCurrTime(dto.getCurrTime());
    data.setSysTime(dto.getSysTime());
    data.setUp(up);
    data.setDown(down);
    return data;
  }

  public static TrainDataDto mapToTrainDataDto(TrainScheduleDto trainScheduleDto, String line, String station) {
    // Validate input
    if (trainScheduleDto == null || trainScheduleDto.getData() == null) {
        throw new IllegalArgumentException("Train schedule data is null or missing.");
    }

    // Construct the key using the line and station
    String key = line + "-" + station;

    // Retrieve the station-specific schedule
    StationScheduleDto stationSchedule = trainScheduleDto.getData().get(key);

    if (stationSchedule == null) {
        throw new IllegalArgumentException("No schedule found for the given line and station: " + key);
    }

    // Map data to TrainDataDto
    TrainDataDto trainDataDto = new TrainDataDto();
    trainDataDto.setCurrTime(stationSchedule.getCurrTime());
    trainDataDto.setSysTime(stationSchedule.getSysTime());

    // Ensure 'UP' and 'DOWN' lists are not null
    trainDataDto.setUp(stationSchedule.getUp() != null ? stationSchedule.getUp() : List.of());
    trainDataDto.setDown(stationSchedule.getDown() != null ? stationSchedule.getDown() : List.of());

    return trainDataDto;
  }
}
