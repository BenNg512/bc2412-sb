package com.bootcamp.sbex2.bc_forum.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.model.dto.PostDto;

public interface PostOperation {
  @GetMapping(value = "/posts")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<PostDto>> getAllPosts();
  
  @PostMapping(value = "/posts")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResp<List<PostDto>> fetchAndSavePosts();
}
