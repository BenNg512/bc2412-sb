package com.bootcamp.sbex2.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.map.EntityMapper;
import com.bootcamp.sbex2.bc_forum.lib.ApiEndpoint;
import com.bootcamp.sbex2.bc_forum.model.dto.CommentDto;
import com.bootcamp.sbex2.bc_forum.repository.CommentRepository;
import com.bootcamp.sbex2.bc_forum.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
  

  @Value("${api.jsonplaceholder.domain}")
  public String domain;

  @Value("${api.jsonplaceholder.endpoints.comments}")
  public String usersEndpoint;

  @Autowired
  public RestTemplate restTemplate;

  @Autowired
  public CommentRepository commentRepository;

  @Autowired
  public EntityMapper entityMapper;
  
  @Override
  public List<CommentDto> getAllComments() {
  String url = ApiEndpoint.COMMENTS.httpsBuilder(domain);

  List<CommentDto> commentDtos = Arrays.asList(this.restTemplate.getForObject(url, CommentDto[].class));

  return commentDtos;
  }

  // @Transactional
  @Override
  public List<CommentDto> fetchAndSaveComments() {
      List<CommentDto> commentDtos = this.getAllComments();
      this.commentRepository.deleteAll();

      commentDtos.stream().forEach(e -> {
        CommentEntity commentEntity = entityMapper.map(e);
        this.commentRepository.save(commentEntity);
      });
      return commentDtos;
  }
}
