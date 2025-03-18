package com.bootcamp.web.demo_coin_web.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.web.demo_coin_web.model.dto.CoinGeckoMarketDto;
import com.bootcamp.web.demo_coin_web.service.CoinService;

@Service
public class CoinServiceImpl implements CoinService {
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<CoinGeckoMarketDto> getCoinMarket() {
    String url =
        "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd";
    return Arrays.asList(
        this.restTemplate.getForObject(url, CoinGeckoMarketDto[].class));
  }
}
