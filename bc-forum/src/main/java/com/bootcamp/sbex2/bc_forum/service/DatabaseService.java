package com.bootcamp.sbex2.bc_forum.service;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;

public interface DatabaseService {
  List<CommentEntity> getAllComments();
  List<CommentEntity> getCommentsByPostId(Integer postId);
  CommentEntity addCommentToPost(Long postId, CommentEntity comment);
  CommentEntity patchComment(@RequestParam Long commentId, @RequestBody CommentEntity comment);
  List<PostEntity> getAllPosts();
  List<UserEntity> getAllUsers();
  List<UserDTO> getAllUserDTOs();
}
