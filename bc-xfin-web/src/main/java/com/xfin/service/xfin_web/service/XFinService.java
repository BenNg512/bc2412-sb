package com.xfin.service.xfin_web.service;

import com.xfin.service.xfin_web.model.FiveMinDataDto;

public interface XFinService {
  public FiveMinDataDto getFiveMinData(String symbol);
  
}
