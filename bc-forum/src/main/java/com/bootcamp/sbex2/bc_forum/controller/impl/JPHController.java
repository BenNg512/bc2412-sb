package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.codewave.SysCode;
import com.bootcamp.sbex2.bc_forum.controller.JPHOperation;
import com.bootcamp.sbex2.bc_forum.dto.UserCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.mapper.UserCommentDTOMapper;
import com.bootcamp.sbex2.bc_forum.dto.mapper.UserPostCommentDTOMapper;
import com.bootcamp.sbex2.bc_forum.service.impl.JPHServiceImpl;


@RestController
public class JPHController implements JPHOperation {

    @Autowired
    private JPHServiceImpl apiService;
    
    @Autowired
    private UserPostCommentDTOMapper UserPostCommentDTOMapper;

    @Autowired
    private UserCommentDTOMapper userCommentDTOMapper;

    @GetMapping("/original/all-users-posts-comments")
    public List<UserDTO> getUsers() {
        return apiService.getUsers();
    }

    // http://localhost:8005/users-with-posts-and-comments
    @Override
    public List<UserPostCommentDTO> getUsersPostsComments() {
        List<UserDTO> users = apiService.getUsers();
        List<UserPostCommentDTO> userPostCommentDTO = UserPostCommentDTOMapper.map(users);
        return userPostCommentDTO;
    } 

    // http://localhost:8005/user/all-comments/?userId=1
    @Override
    public ApiResp<UserCommentDTO> getUserComments(@RequestParam String userId) {

        Long userLongId;
        try {
            userLongId = Long.valueOf(userId);
        } catch (NumberFormatException e) {
            return ApiResp.<UserCommentDTO>builder()
                .syscode(SysCode.INVALID_INPUT)
                .build();
        } 

        List<UserDTO> users = apiService.getUsers();
        boolean userExist = users.stream()
            .anyMatch(user -> user.getId().equals(userLongId));
        if (!userExist) {
            return ApiResp.<UserCommentDTO>builder()
                .syscode(SysCode.USER_NOT_FOUND)
                .build();
        }

        UserDTO user = apiService.getUserComments(userLongId);
        UserCommentDTO userCommentDTO = userCommentDTOMapper.map(user);
        return ApiResp.<UserCommentDTO>builder()
            .syscode(SysCode.OK)
            .data(userCommentDTO)
            .build();
    }

}

