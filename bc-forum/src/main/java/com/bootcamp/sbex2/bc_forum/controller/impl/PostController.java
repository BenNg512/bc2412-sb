package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.codewave.SysCode;
import com.bootcamp.sbex2.bc_forum.controller.PostOperation;
import com.bootcamp.sbex2.bc_forum.model.dto.PostDto;
import com.bootcamp.sbex2.bc_forum.service.PostService;

@RestController
public class PostController implements PostOperation {
  @Autowired
  private PostService postService;

  // http://localhost:8005/jsonplaceholder/posts
  @Override
  public List<PostDto> getAllPosts() {
    return this.postService.getAllPosts();
  }

  @Override
  public ApiResp<Void> fetchAndSavePosts(){
    this.postService.fetchAndSavePosts();
    return ApiResp.<Void>builder()
          .syscode(SysCode.CREATED)
          .build();
  }
}
