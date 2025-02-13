package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto;

import lombok.Getter;

@Getter
public class PostDto {
  Long userId;
  Long id;
  String title;
  String body;
}
