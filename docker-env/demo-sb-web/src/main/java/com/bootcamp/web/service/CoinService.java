package com.bootcamp.web.service;

import java.util.List;
import com.bootcamp.web.model.CoinDto;

public interface CoinService {
  public List<CoinDto> getCoins();
}
