package com.bootcamp.sbex2.bc_forum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {
  Long postId;
  Long id;
  String name;
  String email;
  String body;
}
