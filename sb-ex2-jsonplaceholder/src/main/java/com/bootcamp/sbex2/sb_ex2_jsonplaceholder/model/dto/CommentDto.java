package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto;

import lombok.Getter;

@Getter
public class CommentDto {
  Long postId;
  Long id;
  String name;
  String email;
  String body;
}
