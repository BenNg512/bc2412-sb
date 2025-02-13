package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private List<PostDTO> posts;
}
