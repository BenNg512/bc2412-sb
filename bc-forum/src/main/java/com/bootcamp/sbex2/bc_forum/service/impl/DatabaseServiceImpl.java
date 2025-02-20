package com.bootcamp.sbex2.bc_forum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;
import com.bootcamp.sbex2.bc_forum.repository.CommentRepository;
import com.bootcamp.sbex2.bc_forum.repository.PostRepository;
import com.bootcamp.sbex2.bc_forum.repository.UserRepository;
import com.bootcamp.sbex2.bc_forum.service.DatabaseService;

@Service
public class DatabaseServiceImpl implements DatabaseService {
  @Autowired
  CommentRepository commentRepository;
  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;

  public List<CommentEntity> getAllComments(){
      return commentRepository.findAllByOrderByIdAsc();
    }
  public List<PostEntity> getAllPosts(){
      return postRepository.findAll();
    }
  public List<UserEntity> getAllUsers(){
      return userRepository.findAll();
    }
  
}
