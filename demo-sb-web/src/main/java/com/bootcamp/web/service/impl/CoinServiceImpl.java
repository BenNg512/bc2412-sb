package com.bootcamp.web.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.web.model.CoinDto;
import com.bootcamp.web.service.CoinService;

@Service
public class CoinServiceImpl implements CoinService{
    @Autowired
    RestTemplate restTemplate;

    public List<CoinDto> getCoins(){
    String url = "http://localhost:8080/coins";

    List<CoinDto> coinDto = Arrays.asList(this.restTemplate.getForObject(url, CoinDto[].class));
    // CoinEntity coinEntity = 
    // this.redisManager.get("coins", CoinEntity.class);
    
    return coinDto;
    }
}
