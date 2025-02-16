package com.bootcamp.sbex2.bc_forum.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDTO {
  private Long userId;
  private Long id;
  private String title;
  private String body;
  private List<CommentDTO> comments;

}
