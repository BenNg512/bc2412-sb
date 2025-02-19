package com.bootcamp.sbex2.bc_forum.service;

import java.util.List;
import com.bootcamp.sbex2.bc_forum.model.dto.PostDto;

public interface PostService {
  List<PostDto> getAllPosts();
  List<PostDto> fetchAndSavePosts();
}
