package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.dto.UserDTO;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.impl.ApiService;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/users-with-posts-and-comments")
    public List<UserDTO> getUsersWithPostsAndComments() {
        return apiService.getUsersWithPostsAndComments();
    }
}

