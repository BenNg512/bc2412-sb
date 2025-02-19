package com.bootcamp.sbex2.bc_forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.sbex2.bc_forum.service.CommentService;
import com.bootcamp.sbex2.bc_forum.service.PostService;

@Component // bean
public class PreServerStartConfig implements CommandLineRunner {
  @Autowired
  CommentService commentService;

  @Autowired
  PostService postService;
  
  @Override
  public void run(String... args) throws Exception {
    // call JPH users api
    // call JPH post api
    try{
      this.postService.getAllPosts();
      System.out.println("Posts Data Ready");
    }catch(Exception e){
      System.out.println("Error while fetching posts");
    }
    // call JPH comment api
    try{
      this.commentService.getAllComments();
      System.out.println("Comments Data Ready");
    }catch(Exception e){
      System.out.println("Error while fetching comments");
    }
    // save DB
    

  }
}
