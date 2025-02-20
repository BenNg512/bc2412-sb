package com.bootcamp.sbex2.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;

@RequestMapping(value = "/database")
public interface DatabaseOperation {

  @GetMapping(value = "/comments")
  public List<CommentEntity> getAllComments();
  
  @GetMapping(value = "/posts")
  public List<PostEntity> getAllPosts();
  
  @GetMapping(value = "/users")
  public List<UserEntity> getAllUsers();
  
}
