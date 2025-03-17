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
  
  private final RedisTemplate<String, String> redisTemplate;
  private final ObjectMapper objectMapper;

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

  // method 1
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
  // method 2
  public <T> T get2(String key, TypeReference<T> typeRef) throws JsonProcessingException {
    String json = this.redisTemplate.opsForValue().get(key);
    return json == null ? null : objectMapper.readValue(json, typeRef);
  }
  // method 3 :hardcode
  public List<StockSymbolEntity> getStockSymbols() throws JsonProcessingException {
    String json = this.redisTemplate.opsForValue().get("stockSymbols");
    return json == null ? null : objectMapper.readValue(json, new TypeReference<List<StockSymbolEntity>>() {});
  }

  @SuppressWarnings({"null", "deprecation"})
  public void redisClearAllData() {
    this.redisTemplate.getConnectionFactory().getConnection().flushAll();
    System.out.println("All Redis data has been cleared.");
  }

  public void clearData(String key) {
    this.redisTemplate.delete(key);
  }

}
