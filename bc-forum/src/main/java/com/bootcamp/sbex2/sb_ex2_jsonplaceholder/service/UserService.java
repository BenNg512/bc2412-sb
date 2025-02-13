package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service;

import java.util.List;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.model.dto.UserDto;

public interface UserService {
  List<UserDto> getUsers();
  
}
