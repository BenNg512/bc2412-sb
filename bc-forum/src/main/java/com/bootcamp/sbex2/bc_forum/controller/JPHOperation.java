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
  @GetMapping("/users")
  @ResponseStatus(HttpStatus.OK)
  public List<UserPostComment> getUsers();

  @GetMapping("/users/posts/comments")
  @ResponseStatus(HttpStatus.OK)
  public List<UserPostCommentDTO> getUsersPostsComments();

  @GetMapping("/users/comments")
  @ResponseStatus(HttpStatus.OK)
  ApiResp<UserCommentDTO> getUserComments(@RequestParam String userId);
}
