package com.coin.demo_coin.service;

import java.util.List;
import java.util.Optional;
import com.coin.demo_coin.entity.CoinEntity;
import com.coin.demo_coin.model.CoinDto;

public interface CoinService {
  List<CoinDto> getCoins();
  public void saveCoin(List<CoinEntity> coin);
  public Optional<CoinEntity> getCoin(String id);
  public List<CoinEntity> fetchCoinDataFromApi();
}
