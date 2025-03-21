package com.bootcamp.demo.demo_swagger.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StockDTO {
  private String symbol;
  private String description;
}
