package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto.UserDto;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.endpoints.users}")
  private String usersEndpoint;

  @Override
  public List<UserDto> getUsers() {
    String url = UriComponentsBuilder.newInstance()
      .scheme("https")
      .host(domain)
      .path(usersEndpoint)
      .build()
      .toUriString();

    RestTemplate restTemplate = new RestTemplate();
    UserDto[] results = restTemplate.getForObject(url, UserDto[].class);

    return Arrays.asList(results);
  }
}
