package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto.PostDto;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.PostService;

@Service
public class PostServiceImpl implements PostService {

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.endpoints.posts}")
  private String usersEndpoint;

  @Override
  public List<PostDto> getPosts() {
    String url = UriComponentsBuilder.newInstance()
      .scheme("https")
      .host(domain)
      .path(usersEndpoint)
      .build()
      .toUriString();

    RestTemplate restTemplate = new RestTemplate();
    PostDto[] results = restTemplate.getForObject(url, PostDto[].class);

    return Arrays.asList(results);
  }
  
}
