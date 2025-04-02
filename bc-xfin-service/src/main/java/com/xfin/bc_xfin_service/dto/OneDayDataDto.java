package com.xfin.bc_xfin_service.dto;

import java.util.List;
import com.xfin.bc_xfin_service.entity.StockPriceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OneDayDataDto {
  private String lastUpdted;
  private List<StockPriceEntity> data;
  
}
