package com.bootcamp.sbex2.bc_forum.model.dto;

import lombok.Getter;

@Getter
public class PostDto {
  Long userId;
  Long id;
  String title;
  String body;
}
