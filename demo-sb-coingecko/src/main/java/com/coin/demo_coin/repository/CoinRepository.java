package com.coin.demo_coin.repository;

import org.springframework.data.repository.CrudRepository;
import com.coin.demo_coin.entity.CoinEntity;

public interface CoinRepository extends CrudRepository<CoinEntity, String> {
}
