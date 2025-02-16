package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO;
import com.bootcamp.sbex2.bc_forum.service.impl.ApiService;


@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;
    
    @Autowired
    private com.bootcamp.sbex2.bc_forum.dto.mapper.UserPostCommentDTOMapper DTOMapper;

    @GetMapping("/original/users-with-posts-and-comments")
    public List<UserDTO> getUsers() {
        return apiService.getUsers();
    }

    // http://localhost:8005/users-with-posts-and-comments
    @GetMapping("/users-with-posts-and-comments")
    public List<UserPostCommentDTO> getUsersPostsComments() {
        List<UserDTO> users = apiService.getUsers();
        List<UserPostCommentDTO> userPostCommentDTOS = DTOMapper.map(users);
        return userPostCommentDTOS;
    }

}

