package com.bootcamp.web.demo_coin_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.web.demo_coin_web.model.dto.CalculatorResult;

@RestController
public class CalculatorController {
  @Value("${api.backend.host}")
  private String host;

  @Value("${api.backend.endpoint}")
  private String endpoint;

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping(value = "/calculate/{x}/{y}")
  public Integer calculate(@PathVariable Integer x, @PathVariable Integer y) {
    String url = "http://" + host + endpoint;
    url = url.replace("{x}", String.valueOf(x));
    url = url.replace("{y}", String.valueOf(y));
    System.out.println("url=" + url);
    return this.restTemplate.getForObject(url, CalculatorResult.class).getValue();
  }
}
