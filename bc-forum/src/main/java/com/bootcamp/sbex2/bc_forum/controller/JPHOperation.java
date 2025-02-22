package com.bootcamp.sbex2.bc_forum.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.dto.UserCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO;
import com.bootcamp.sbex2.bc_forum.model.dto.UserPostComment;

@RequestMapping("/jph")
public interface JPHOperation {
  // http://localhost:8005/jph/users
  @GetMapping("/users")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<UserPostComment>> getUsers();

  // http://localhost:8005/jph/users/posts/comments
  @GetMapping("/users/posts/comments")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<UserPostCommentDTO>> getUsersPostsComments();

  // http://localhost:8005/jph/users/comments?userId=1
  @GetMapping("/users/comments")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<UserCommentDTO> getUserComments(@RequestParam String userId);
}
