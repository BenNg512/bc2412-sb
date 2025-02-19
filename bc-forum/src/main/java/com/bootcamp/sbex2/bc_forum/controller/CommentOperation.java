package com.bootcamp.sbex2.bc_forum.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.model.dto.CommentDto;

public interface CommentOperation {
  @GetMapping(value = "/comments")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<CommentDto>> getAllComments();

  @PostMapping(value = "/comments")
  @ResponseStatus(HttpStatus.CREATED) // 201
  public ApiResp<Void> fetchAndSaveComments();
}


