package com.xfin.bc_xfin_service.codewave;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfin.bc_xfin_service.entity.StockSymbolEntity;

public class RedisManager {
  private static final Duration DEFAULT_DURATION = Duration.ofHours(1);
  
  private RedisTemplate<String, String> redisTemplate;
  private ObjectMapper objectMapper;

  public RedisManager(RedisConnectionFactory factory,
      ObjectMapper objectMapper) {
    Objects.requireNonNull(factory);
    Objects.requireNonNull(objectMapper);
    this.redisTemplate = new RedisTemplate<>();
    this.redisTemplate.setConnectionFactory(factory);
    this.redisTemplate.setKeySerializer(RedisSerializer.string());
    this.redisTemplate.setValueSerializer(RedisSerializer.json());
    this.redisTemplate.afterPropertiesSet();
    this.objectMapper = objectMapper;
  }

  public <T> T get(String key, Class<T> clazz) throws JsonProcessingException {
    String json = this.redisTemplate.opsForValue().get(key);
    return json == null ? null : objectMapper.readValue(json, clazz);
  }

  public void set(String key, Object object) throws JsonProcessingException {
    String serializedJson = objectMapper.writeValueAsString(object);
    this.redisTemplate.opsForValue().set(key, serializedJson, DEFAULT_DURATION);
  }

  public void set(String key, Object object, Duration duration)
      throws JsonProcessingException {
    String serializedJson = objectMapper.writeValueAsString(object);
    this.redisTemplate.opsForValue().set(key, serializedJson, duration);
  }

  //
  // for this project only

  public void saveStockSymbols(List<StockSymbolEntity> entities) throws JsonProcessingException {
    String key = "stockSymbols";
    this.set(key, entities);
  }

  public List<StockSymbolEntity> getStockSymbols() throws JsonProcessingException {
    String key = "stockSymbols";
    String json = this.redisTemplate.opsForValue().get(key);
    return json == null ? null : objectMapper.readValue(json, new TypeReference<List<StockSymbolEntity>>() {});
  }

  @SuppressWarnings({"null", "deprecation"})
  public void clearAllData() {
    try {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
        System.out.println("All Redis data has been cleared.");
    } catch (Exception e) {
        System.err.println("Error while clearing Redis data: " + e.getMessage());
    }
  }

}
