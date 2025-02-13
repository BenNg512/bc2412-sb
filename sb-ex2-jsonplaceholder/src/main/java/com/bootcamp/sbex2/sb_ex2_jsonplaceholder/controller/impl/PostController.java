package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto.PostDto;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.PostService;

@RestController
public class PostController{
  @Autowired
  private PostService postService;

  @GetMapping(value = "jsonplaceholder/posts")
  public List<PostDto> getPosts() {
    return this.postService.getPosts();
  }
}
