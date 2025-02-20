package com.bootcamp.sbex2.bc_forum.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;

@RequestMapping(value = "/database")
public interface DatabaseOperation {

  @GetMapping(value = "/comments")
  @ResponseStatus(HttpStatus.OK)
  public List<CommentEntity> getAllComments();
  
  @GetMapping(value = "/posts")
  @ResponseStatus(HttpStatus.OK)
  public List<PostEntity> getAllPosts();
  
  @GetMapping(value = "/users")
  @ResponseStatus(HttpStatus.OK)
  public List<UserEntity> getAllUsers();

  @GetMapping(value = "/userDTOs")
  @ResponseStatus(HttpStatus.OK)
  public List<UserDTO> getAllUserDTOs();
  
}
