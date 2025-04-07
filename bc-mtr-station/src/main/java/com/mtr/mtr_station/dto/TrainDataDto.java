package com.mtr.mtr_station.dto;

import java.util.List;
import com.mtr.mtr_station.model.TrainInfoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainDataDto {
  private String currTime;
  private String sysTime;
  private List<TrainInfoDto> up;
  private List<TrainInfoDto> down;
}
