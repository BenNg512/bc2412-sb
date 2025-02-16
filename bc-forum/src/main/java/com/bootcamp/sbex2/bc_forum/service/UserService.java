package com.bootcamp.sbex2.bc_forum.service;

import java.util.List;
import com.bootcamp.sbex2.bc_forum.model.dto.UserDto;

public interface UserService {
  List<UserDto> getAllUsers();
  
}
