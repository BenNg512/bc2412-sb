package com.bootcamp.sbex2.bc_forum.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;

@RequestMapping(value = "/database")
public interface DatabaseOperation {

  @GetMapping(value = "/comments")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<CommentEntity>> getAllComments();

  // http://localhost:8005/database/comments/id?postId=100
  @GetMapping(value = "/comments/id")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<CommentEntity>> getCommentById(@RequestParam Integer postId);

  // http://localhost:8005/database/comments?postId=1
  @PostMapping(value = "/comments")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResp<CommentEntity> addComment(@RequestParam Long postId, 
                                          @RequestBody CommentEntity comment);

  // http://localhost:8005/database/comments?commentId=1
  @PatchMapping(value = "/comments")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<CommentEntity> patchComment(@RequestParam Long commentId, 
                                            @RequestBody CommentEntity comment);
  
  @GetMapping(value = "/posts")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<PostEntity>> getAllPosts();

  // http://localhost:8005/database/users/2/posts
  @GetMapping("/users/{userId}/posts")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<PostEntity>> getAllPostsByUserId(@PathVariable("userId") Integer userId);
  
  @GetMapping(value = "/v0//users")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<UserEntity>> getAllUsers();

  @GetMapping(value = "/users")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<UserDTO>> getAllUserDTOs();
  
}
