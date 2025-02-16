package com.bootcamp.sbex2.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.dto.UserCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO;

public interface ApiOperation {
  @GetMapping("/users-with-posts-and-comments")
  public List<UserPostCommentDTO> getUsersPostsComments();
  
  @GetMapping("/user/all-comments/")
  ApiResp<UserCommentDTO> getUserComments(@RequestParam String userId);
}
