package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.controller.DatabaseApiOperation;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;
import com.bootcamp.sbex2.bc_forum.service.DatabaseApiService;

@RestController
public class DatabaseApiController implements DatabaseApiOperation {
  @Autowired
  DatabaseApiService databaseApiService;
  @Override
  public List<CommentEntity> getAllComments() {
    return databaseApiService.getAllComments();
  }

  @Override
  public List<PostEntity> getAllPosts() {
    return databaseApiService.getAllPosts();
  }

  @Override
  public List<UserEntity> getAllUsers() {
    return databaseApiService.getAllUsers();
  }
  
}
