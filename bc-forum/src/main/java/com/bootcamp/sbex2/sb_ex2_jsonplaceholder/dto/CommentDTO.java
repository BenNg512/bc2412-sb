package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}
