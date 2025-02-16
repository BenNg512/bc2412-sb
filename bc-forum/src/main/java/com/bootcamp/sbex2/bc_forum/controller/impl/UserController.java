package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.model.dto.UserDto;
import com.bootcamp.sbex2.bc_forum.service.UserService;

@RestController
public class UserController{
  @Autowired
  private UserService userService;

  // http://localhost:8005/jsonplaceholder/users
  @GetMapping(value = "/jsonplaceholder/users") 
  public List<UserDto> getAllUsers() {
    return this.userService.getAllUsers();
  }

}
