package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service;

import java.util.List;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto.PostDto;

public interface PostService {
  List<PostDto> getPosts();
  
}
