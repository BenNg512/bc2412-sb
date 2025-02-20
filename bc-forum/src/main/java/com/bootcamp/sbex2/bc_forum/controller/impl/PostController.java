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
  public ApiResp<List<PostDto>> getAllPosts() {
    return ApiResp.<List<PostDto>>builder()
        .syscode(SysCode.OK)
        .data(this.postService.getAllPosts())
        .build();
  }

  @Override
  public ApiResp<List<PostDto>> fetchAndSavePosts(){
    return ApiResp.<List<PostDto>>builder()
          .syscode(SysCode.CREATED)
          .data(this.postService.fetchAndSavePosts())
          .build();
  }
}
