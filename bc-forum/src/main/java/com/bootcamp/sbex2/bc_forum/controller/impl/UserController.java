package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.model.dto.UserDto;
import com.bootcamp.sbex2.bc_forum.service.UserService;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.codewave.SysCode;
import com.bootcamp.sbex2.bc_forum.controller.UserOperation;
@RestController
public class UserController implements UserOperation {
  @Autowired
  private UserService userService;

  // http://localhost:8005/jsonplaceholder/users
  @Override
  public ApiResp<List<UserDto>> getAllUsers() {
    return ApiResp.<List<UserDto>>builder()
        .syscode(SysCode.OK)
        .data(this.userService.getAllUsers())
        .build();
  }

  public ApiResp<List<UserDto>> fetchAndSaveUsers() {
    // List<UserDto> data = this.userService.fetchAndSaveAllUsers();
    return ApiResp.<List<UserDto>>builder()
        .syscode(SysCode.CREATED)
        //the method will be called here which database will be updated once
        // so don't put the method twice
        .data(this.userService.fetchAndSaveAllUsers())
        .build();
  }

}
