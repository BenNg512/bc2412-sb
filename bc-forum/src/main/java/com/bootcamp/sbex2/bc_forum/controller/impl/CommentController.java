package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.codewave.SysCode;
import com.bootcamp.sbex2.bc_forum.controller.CommentOperation;
import com.bootcamp.sbex2.bc_forum.model.dto.CommentDto;
import com.bootcamp.sbex2.bc_forum.service.CommentService;

@RestController
public class CommentController implements CommentOperation {
  @Autowired
  private CommentService commentService;

  @Override
  public ApiResp<List<CommentDto>> getAllComments() {
    return ApiResp.<List<CommentDto>>builder() //
        .syscode(SysCode.OK) //
        .data(this.commentService.getAllComments()) //
        .build();
  }

  @Override
    public ApiResp<List<CommentDto>> fetchAndSaveComments() {
        return ApiResp.<List<CommentDto>>builder()
          .syscode(SysCode.CREATED)
          .data(this.commentService.getAllComments())
          .build();
    }
}
