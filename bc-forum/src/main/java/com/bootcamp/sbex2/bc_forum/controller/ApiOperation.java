package com.bootcamp.sbex2.bc_forum.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.dto.UserCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO;

public interface ApiOperation {
  @GetMapping("/get-all-data")
  @ResponseStatus(HttpStatus.OK)
  public List<UserDTO> getUsers();

  @GetMapping("/users-posts-comments")
  @ResponseStatus(HttpStatus.OK)
  public List<UserPostCommentDTO> getUsersPostsComments();

  @GetMapping("/user/all-comments/")
  @ResponseStatus(HttpStatus.OK)
  ApiResp<UserCommentDTO> getUserComments(@RequestParam String userId);
}
