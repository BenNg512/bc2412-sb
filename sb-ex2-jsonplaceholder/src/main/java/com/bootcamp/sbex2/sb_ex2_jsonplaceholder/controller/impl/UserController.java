package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.controller.UserOperation;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto.PostDto;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto.UserDto;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.PostService;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.UserService;

@RestController
public class UserController implements UserOperation {
  @Autowired
  private UserService userService;
  @Autowired
  private PostService postService;

  @GetMapping(value = "/jsonplaceholder/users") // http://localhost:8005/jsonplaceholder/users
  public List<UserDto> getUsers() {
    return this.userService.getUsers();
  }
  @GetMapping(value = "jsonplaceholder/posts")
  public List<PostDto> getPosts() {
    return this.postService.getPosts();
  }
}
