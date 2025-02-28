package com.coin.demo_coin.service;

import java.util.List;
import java.util.Optional;
import com.coin.demo_coin.entity.Coin;
import com.coin.demo_coin.model.CoinDto;

public interface CoinService {
  List<CoinDto> getCoins();
  public void saveCoin(List<Coin> coin);
  public Optional<Coin> getCoin(String id);
  public List<Coin> fetchCoinDataFromApi();
}
