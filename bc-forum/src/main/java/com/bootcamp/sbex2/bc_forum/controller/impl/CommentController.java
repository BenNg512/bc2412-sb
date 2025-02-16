package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.model.dto.CommentDto;
import com.bootcamp.sbex2.bc_forum.service.CommentService;

@RestController
public class CommentController {
    @Autowired
  private CommentService commentService;

  // http://localhost:8005/jsonplaceholder/comments
  @GetMapping(value = "/jsonplaceholder/comments")
  public List<CommentDto> getAllComments() {
    return this.commentService.getAllComments();
  }
  
}
