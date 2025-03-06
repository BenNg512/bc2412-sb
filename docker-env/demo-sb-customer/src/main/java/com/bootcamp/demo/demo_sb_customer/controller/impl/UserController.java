package com.bootcamp.demo.demo_sb_customer.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_customer.dto.UserDTO;
import com.bootcamp.demo.demo_sb_customer.dto.mapper.UserDTOMapper;
import com.bootcamp.demo.demo_sb_customer.model.dto.UserDto;
import com.bootcamp.demo.demo_sb_customer.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private UserDTOMapper userDTOMapper;

  @GetMapping(value = "/jsonplaceholder/users")
  public List<UserDTO> getUsers() throws JsonProcessingException {
    // List of UserDto -> List of UserDTO
    List<UserDto> userDtos = this.userService.getUsers();
    System.out.println("userDtos=" + userDtos);
    return userDtos.stream() //
        .map(e -> userDTOMapper.map(e)) //
        .collect(Collectors.toList());
    // List<UserDto> serviceResults = this.userService.getUsers();
    // List<UserDTO> userDTOs = new ArrayList<>();
    // for (UserDto dto : serviceResults) {
    // UserDTO userDTO = userDTOMapper.map(dto);
    // userDTOs.add(userDTO);
    // }
    // return userDTOs;
  }
}
