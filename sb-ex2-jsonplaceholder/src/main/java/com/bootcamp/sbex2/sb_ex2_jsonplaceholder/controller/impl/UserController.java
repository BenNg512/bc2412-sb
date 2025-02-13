package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto.UserDto;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.UserService;

@RestController
public class UserController{
  @Autowired
  private UserService userService;

  @GetMapping(value = "/jsonplaceholder/users") // http://localhost:8005/jsonplaceholder/users
  public List<UserDto> getUsers() {
    return this.userService.getUsers();
  }

}
