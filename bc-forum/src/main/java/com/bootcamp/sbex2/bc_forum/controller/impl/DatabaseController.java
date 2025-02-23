package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.codewave.SysCode;
import com.bootcamp.sbex2.bc_forum.controller.DatabaseOperation;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;
import com.bootcamp.sbex2.bc_forum.repository.CommentRepository;
import com.bootcamp.sbex2.bc_forum.repository.PostRepository;
import com.bootcamp.sbex2.bc_forum.repository.UserRepository;
import com.bootcamp.sbex2.bc_forum.service.DatabaseService;

@RestController
public class DatabaseController implements DatabaseOperation {
  @Autowired
  DatabaseService databaseApiService;
  @Autowired
  CommentRepository commentRepository;
  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;

  @Override
  public ApiResp<List<CommentEntity>> getAllComments() {
    return ApiResp.<List<CommentEntity>>builder()
        .syscode(SysCode.OK)
        .data(databaseApiService.getAllComments())
        .build();
  }

  @Override
  public ApiResp<List<CommentEntity>> getCommentById(@RequestParam Long postId){
    if (!this.postRepository.findById(postId).isPresent())
      return ApiResp.<List<CommentEntity>>builder()
          .syscode(SysCode.POST_NOT_FOUND)
          .build();
    else 
      return ApiResp.<List<CommentEntity>>builder()
          .syscode(SysCode.OK)
          .data(databaseApiService.getCommentsByPostId(postId))
          .build();
  }

  @Override
  public ApiResp<CommentEntity> addComment(@RequestParam Long postId, @RequestBody CommentEntity comment) {
    if (!this.postRepository.findById(postId).isPresent())
      return ApiResp.<CommentEntity>builder()
          .syscode(SysCode.POST_NOT_FOUND)
          .build();
    else
      return ApiResp.<CommentEntity>builder()
          .syscode(SysCode.CREATED)
          .data(databaseApiService.addCommentToPost(postId, comment))
          .build();
  }

  @Override
  public ApiResp<CommentEntity> patchComment(@RequestParam Long commentId, @RequestBody CommentEntity comment){
    if (!this.commentRepository.findById(commentId).isPresent())
      return ApiResp.<CommentEntity>builder()
          .syscode(SysCode.POST_NOT_FOUND)
          .build();
    else
      return ApiResp.<CommentEntity>builder()
          .syscode(SysCode.CREATED)
          .data(databaseApiService.patchComment(commentId, comment))
          .build();
  }

  @Override
  public ApiResp<List<PostEntity>> getAllPosts() {
    return ApiResp.<List<PostEntity>>builder()
        .syscode(SysCode.OK)
        .data(databaseApiService.getAllPosts())
        .build();
  }

  @Override
  public ApiResp<List<PostEntity>> getAllPostsByUserId(Long userId){
    if (!this.userRepository.findById(userId).isPresent())
    return ApiResp.<List<PostEntity>>builder()
        .syscode(SysCode.USER_NOT_FOUND)
        .build();
    return ApiResp.<List<PostEntity>>builder()
        .syscode(SysCode.OK)
        .data(databaseApiService.getPostsByUserId(userId))
        .build();
  }

  @Override
  public ApiResp<PostEntity> addPost(Long userId, PostEntity postEntity){
    if (!this.userRepository.findById(userId).isPresent())
    return ApiResp.<PostEntity>builder()
        .syscode(SysCode.USER_NOT_FOUND)
        .build();
    return ApiResp.<PostEntity>builder()
        .syscode(SysCode.OK)
        .data(this.databaseApiService.addPostByUserId(userId, postEntity))
        .build();
  }

  @Override
  public ApiResp<PostEntity> deletePost(@PathVariable("postId") Long postId){
    if (!this.postRepository.findById(postId).isPresent())
      return ApiResp.<PostEntity>builder()
          .syscode(SysCode.POST_NOT_FOUND)
          .build();
    else
      this.databaseApiService.deletePostAndComments(postId);
      return ApiResp.<PostEntity>builder()
          .syscode(SysCode.OK)
          .build();
  }

  @Override
  public ApiResp<List<UserEntity>> getAllUsers() {
    return ApiResp.<List<UserEntity>>builder()
        .syscode(SysCode.OK)
        .data(databaseApiService.getAllUsers())
        .build();
  }

  @Override
  public ApiResp<List<UserDTO>> getAllUserDTOs() {
    return ApiResp.<List<UserDTO>>builder()
        .syscode(SysCode.OK)
        .data(databaseApiService.getAllUserDTOs())
        .build();
  }
  
}
