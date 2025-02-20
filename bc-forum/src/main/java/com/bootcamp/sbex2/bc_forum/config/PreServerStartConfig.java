package com.bootcamp.sbex2.bc_forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.sbex2.bc_forum.codewave.BusinessException;
import com.bootcamp.sbex2.bc_forum.codewave.SysCode;
import com.bootcamp.sbex2.bc_forum.service.CommentService;
import com.bootcamp.sbex2.bc_forum.service.PostService;
import com.bootcamp.sbex2.bc_forum.service.UserService;

@Component // bean
public class PreServerStartConfig implements CommandLineRunner {
  @Autowired
  CommentService commentService;

  @Autowired
  PostService postService;

  @Autowired
  UserService userService;
  
  @Override
  public void run(String... args) throws Exception {
    // call api
    try{
      this.userService.getAllUsers();
      this.postService.getAllPosts();
      this.commentService.getAllComments();
      System.out.println("API Connection Success");
    }catch(Exception e){
      throw BusinessException.of(SysCode.API_UNAVAILABLE);
    }
    // save DB
    try{
    this.userService.fetchAndSaveAllUsers();
    this.postService.fetchAndSavePosts();
    this.commentService.fetchAndSaveComments();
    System.out.println("Database Connection Success");
    }catch(Exception e){
      throw BusinessException.of(SysCode.DATABASE_CONNECTION_ERROR);
    }

  }
}
