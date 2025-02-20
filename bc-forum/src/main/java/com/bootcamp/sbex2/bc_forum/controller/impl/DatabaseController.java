package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.controller.DatabaseOperation;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;
import com.bootcamp.sbex2.bc_forum.service.DatabaseService;

@RestController
public class DatabaseController implements DatabaseOperation {
  @Autowired
  DatabaseService databaseApiService;
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

  @Override
  public List<UserDTO> getAllUserDTOs() {
    return databaseApiService.getAllUserDTOs();
  }
  
}
