package com.bootcamp.sbex2.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.sbex2.bc_forum.model.dto.CommentDto;
import com.bootcamp.sbex2.bc_forum.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.endpoints.comments}")
  private String usersEndpoint;
  
  @Override
  public List<CommentDto> getAllComments() {
    String url = UriComponentsBuilder.newInstance()
    .scheme("https")
    .host(domain)
    .path(usersEndpoint)
    .build()
    .toUriString();

  RestTemplate restTemplate = new RestTemplate();
  CommentDto[] results = restTemplate.getForObject(url, CommentDto[].class);

  return Arrays.asList(results);
  }
  
}
