package com.xfin.bc_xfin_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfin.bc_xfin_service.codewave.RedisManager;

@Configuration
public class AppConfig {
  @Bean
  RestTemplate restTemplate() {
      return new RestTemplate();
  }

  @Bean
  RedisManager redisManager(RedisConnectionFactory factory, ObjectMapper objectMapper) {
    return new RedisManager(factory, objectMapper);
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

}
