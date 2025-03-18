package com.xfin.service.xfin_web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.xfin.service.xfin_web.model.FiveMinDataDto;
import com.xfin.service.xfin_web.service.XFinService;

@Service
public class XFinServiceImpl implements XFinService {

@Autowired
private RestTemplate restTemplate;

@Override
public FiveMinDataDto getFiveMinData(String symbol) {
  String url = "http://localhost:8101/5min-data/" + symbol;
  return restTemplate.getForObject(url, FiveMinDataDto.class);
}



  
}
