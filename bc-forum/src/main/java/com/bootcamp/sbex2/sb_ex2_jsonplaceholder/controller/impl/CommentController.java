package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto.CommentDto;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.CommentService;

@RestController
public class CommentController {
    @Autowired
  private CommentService commentService;

  @GetMapping(value = "jsonplaceholder/comments")
  public List<CommentDto> getPosts() {
    return this.commentService.getComments();
  }
  
}
