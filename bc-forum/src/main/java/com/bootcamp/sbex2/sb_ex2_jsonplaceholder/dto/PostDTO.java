package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
    public Long getUserId() {
      return this.id;
    }
}
