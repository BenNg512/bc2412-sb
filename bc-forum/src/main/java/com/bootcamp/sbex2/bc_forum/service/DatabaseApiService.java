package com.bootcamp.sbex2.bc_forum.service;

import java.util.List;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;

public interface DatabaseApiService {
  List<CommentEntity> getAllComments();
  List<PostEntity> getAllPosts();
  List<UserEntity> getAllUsers();
  
}
