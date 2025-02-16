package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.model.dto.PostDto;
import com.bootcamp.sbex2.bc_forum.service.PostService;

@RestController
public class PostController{
  @Autowired
  private PostService postService;

  // http://localhost:8005/jsonplaceholder/posts
  @GetMapping(value = "/jsonplaceholder/posts")
  public List<PostDto> getAllPosts() {
    return this.postService.getAllPosts();
  }
}
