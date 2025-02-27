package com.coin.demo_coin.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.coin.demo_coin.entity.CoinEntity;
import com.coin.demo_coin.model.CoinDto;
import com.coin.demo_coin.repository.CoinRepository;
import com.coin.demo_coin.service.CoinService;

  @Service
  public class CoinServiceImpl implements CoinService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CoinRepository coinRepository;

    public List<CoinDto> getCoins(){
    String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd";

    List<CoinDto> coinDto = Arrays.asList(this.restTemplate.getForObject(url, CoinDto[].class));
    // CoinEntity coinEntity = 
    // this.redisManager.get("coins", CoinEntity.class);
    
    return coinDto;
    }
    public void saveCoin(List<CoinEntity> coin) {
      for (CoinEntity coinEntity : coin) {
        coinRepository.save(coinEntity);
      }
    }

    public Optional<CoinEntity> getCoin(String id) {
      return coinRepository.findById(id);
    }

    public List<CoinEntity> fetchCoinDataFromApi() {
      String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd";
        ResponseEntity<List<CoinDto>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<CoinDto>>() {}
        );
        List<CoinDto> coinDtos = response.getBody();
        List<CoinEntity> coinEntities = coinDtos.stream().map(coinDto -> {
            CoinEntity coinEntity = new CoinEntity();
            coinEntity.setId(coinDto.getId());
            coinEntity.setSymbol(coinDto.getSymbol());
            coinEntity.setName(coinDto.getName());
            coinEntity.setImage(coinDto.getImage());
            coinEntity.setCurrentPrice(coinDto.getCurrentPrice());
            coinEntity.setMarketCap(coinDto.getMarketCap());
            coinEntity.setMarketCapRank(coinDto.getMarketCapRank());
            coinEntity.setFullyDilutedValuation(coinDto.getFullyDilutedValuation());
            coinEntity.setTotalVolume(coinDto.getTotalVolume());
            coinEntity.setHigh24h(coinDto.getHigh24h());
            coinEntity.setLow24h(coinDto.getLow24h());  
            coinEntity.setPriceChange24h(coinDto.getPriceChange24h());  
            coinEntity.setPriceChangePercentage24h(coinDto.getPriceChangePercentage24h());  
            coinEntity.setMarketCapChange24h(coinDto.getMarketCapChange24h());  
            coinEntity.setMarketCapChangePercentage24h(coinDto.getMarketCapChangePercentage24h());  
            coinEntity.setCirculatingSupply(coinDto.getCirculatingSupply());  
            coinEntity.setTotalSupply(coinDto.getTotalSupply());  
            coinEntity.setMaxSupply(coinDto.getMaxSupply());  
            coinEntity.setAth(coinDto.getAth());  
            coinEntity.setAthChangePercentage(coinDto.getAthChangePercentage());  
            coinEntity.setAthDate(coinDto.getAthDate());  
            coinEntity.setAtl(coinDto.getAtl());  
            coinEntity.setAtlChangePercentage(coinDto.getAtlChangePercentage());  
            coinEntity.setAtlDate(coinDto.getAtlDate());  
            coinEntity.setLastUpdated(coinDto.getLastUpdated());  
            return coinEntity;
        }).toList();
        return coinEntities;
    }

}

