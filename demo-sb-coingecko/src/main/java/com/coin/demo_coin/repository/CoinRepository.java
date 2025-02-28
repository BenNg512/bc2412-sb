package com.coin.demo_coin.repository;

import org.springframework.data.repository.CrudRepository;
import com.coin.demo_coin.entity.Coin;

public interface CoinRepository extends CrudRepository<Coin, String> {
}
