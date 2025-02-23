package com.bootcamp.sbex2.bc_forum.service;

import java.util.List;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;

public interface DatabaseService {
  List<CommentEntity> getAllComments();
  List<CommentEntity> getCommentsByPostId(Long postId);
  CommentEntity addCommentToPost(Long postId, CommentEntity commentEntity);
  CommentEntity patchComment(Long commentId, CommentEntity commentEntity);
  List<PostEntity> getAllPosts();
  List<PostEntity> getPostsByUserId(Long userId);
  PostEntity addPostByUserId(Long userId, PostEntity postEntity);
  void deletePostAndComments(Long postId);
  List<UserEntity> getAllUsers();
  List<UserDTO> getAllUserDTOs();
}
