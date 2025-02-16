package com.bootcamp.sbex2.bc_forum.service;

import java.util.List;
import com.bootcamp.sbex2.bc_forum.model.dto.CommentDto;

public interface CommentService {
  List <CommentDto> getAllComments();
}
