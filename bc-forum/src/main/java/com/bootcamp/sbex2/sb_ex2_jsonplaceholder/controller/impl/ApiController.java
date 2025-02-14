package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.dto.PostDTO;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.dto.UserDTO;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.impl.ApiServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ApiController {

    @Autowired
    private ApiServiceImpl apiService;

    @GetMapping("/users-with-posts-and-comments")
    public List<UserDTO> getUsersWithPostsAndComments() {
        return apiService.getUsersWithPostsAndComments();
    }

    @GetMapping("/posts-with-comments")
    public List<PostDTO> getAllPostsWithComments() {
        return apiService.getAllPostsWithComments();
    }
    
}

