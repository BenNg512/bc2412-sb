package com.bootcamp.sbex2.bc_forum.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.model.dto.UserDto;

public interface UserOperation {
  @GetMapping(value = "/users")
  @ResponseStatus(HttpStatus.OK)
  public ApiResp<List<UserDto>> getAllUsers();

  @PostMapping(value = "/users")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResp<List<UserDto>> fetchAndSaveUsers();

}
