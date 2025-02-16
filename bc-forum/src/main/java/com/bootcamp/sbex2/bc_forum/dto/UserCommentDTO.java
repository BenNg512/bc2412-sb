package com.bootcamp.sbex2.bc_forum.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCommentDTO {
  private Long id;
  private String username;
  private List<Comment> comments;

  @Getter
  @Setter
  @Builder
  public static class Comment {
    private String name;
    private String email;
    private String body;
  }

}
