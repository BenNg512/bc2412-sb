package com.coin.demo_coin.controller.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.coin.demo_coin.controller.CoinOperation;
import com.coin.demo_coin.entity.CoinEntity;
import com.coin.demo_coin.model.CoinDto;
import com.coin.demo_coin.service.CoinService;

@RestController
public class CoinController implements CoinOperation {
  @Autowired
    private CoinService coinService;
  
    public List<CoinDto> getCoins(){
      return this.coinService.getCoins();
    }

    @PostMapping("/fetch-and-save")
    public void fetchAndSaveCoin() {
        List<CoinEntity> coinEntity = this.coinService.fetchCoinDataFromApi();
        coinService.saveCoin(coinEntity);
    }

    @GetMapping("/{id}")
    public Optional<CoinEntity> getCoinEntity(@PathVariable String coinId) {
        return this.coinService.getCoin(coinId);
    }
  }