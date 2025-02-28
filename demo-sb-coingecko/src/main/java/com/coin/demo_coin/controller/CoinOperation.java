package com.coin.demo_coin.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.coin.demo_coin.model.CoinDto;

public interface CoinOperation {

  @CrossOrigin
  @GetMapping(value = "/coins")
  public List<CoinDto> getCoins();

  @GetMapping(value = "/coins2")
  public List<CoinDto> getCoins2();
}
