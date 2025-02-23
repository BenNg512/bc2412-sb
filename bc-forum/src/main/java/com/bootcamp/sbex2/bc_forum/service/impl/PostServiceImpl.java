package com.bootcamp.sbex2.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.map.EntityMapper;
import com.bootcamp.sbex2.bc_forum.lib.ApiEndpoint;
import com.bootcamp.sbex2.bc_forum.model.dto.PostDto;
import com.bootcamp.sbex2.bc_forum.repository.PostRepository;
import com.bootcamp.sbex2.bc_forum.service.PostService;

@Service
public class PostServiceImpl implements PostService {
  @Autowired
  PostRepository postRepository;

  @Autowired
  EntityMapper entityMapper;

  @Autowired
  RestTemplate restTemplate;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  @Value("${api.jsonplaceholder.endpoints.posts}")
  private String usersEndpoint;

  @Override
  public List<PostDto> getAllPosts() {
    String url = ApiEndpoint.POSTS.httpsBuilder(domain);

    List<PostDto> postDtos = Arrays.asList(this.restTemplate.getForObject(url, PostDto[].class));
    return postDtos;
  }

  @Override
  public List<PostDto> fetchAndSavePosts() {
    List<PostDto> postDtos = this.getAllPosts();
    this.postRepository.deleteAll();

    postDtos.stream().forEach(e -> {
        PostEntity postEntity = entityMapper.map(e);
        this.postRepository.save(postEntity);
      });
    return postDtos;
  }
  
}
