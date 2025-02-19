package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.model.dto.UserDto;
import com.bootcamp.sbex2.bc_forum.repository.UserRepository;
import com.bootcamp.sbex2.bc_forum.service.UserService;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.codewave.SysCode;
import com.bootcamp.sbex2.bc_forum.controller.UserOperation;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;
@RestController
public class UserController implements UserOperation {
  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  // http://localhost:8005/jsonplaceholder/users
  @Override
  public List<UserDto> getAllUsers() {
    return this.userService.getAllUsers();
  }
  
  public ApiResp<Void> fetchAndSaveUsers() {
    this.userService.fetchAndSaveAllUsers();
    return ApiResp.<Void>builder()
        .syscode(SysCode.CREATED)
        .build();
  }

}
