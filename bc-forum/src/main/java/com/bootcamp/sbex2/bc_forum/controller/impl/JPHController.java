package com.bootcamp.sbex2.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.sbex2.bc_forum.codewave.ApiResp;
import com.bootcamp.sbex2.bc_forum.codewave.SysCode;
import com.bootcamp.sbex2.bc_forum.controller.JPHOperation;
import com.bootcamp.sbex2.bc_forum.dto.UserCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserPostCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.mapper.UserCommentDTOMapper;
import com.bootcamp.sbex2.bc_forum.dto.mapper.UserPostCommentDTOMapper;
import com.bootcamp.sbex2.bc_forum.model.dto.UserPostComment;
import com.bootcamp.sbex2.bc_forum.service.impl.JPHServiceImpl;


@RestController
public class JPHController implements JPHOperation {

    @Autowired
    private JPHServiceImpl apiService;
    
    @Autowired
    private UserPostCommentDTOMapper UserPostCommentDTOMapper;

    @Autowired
    private UserCommentDTOMapper userCommentDTOMapper;

    public ApiResp<List<UserPostComment>> getUsers() {
        return ApiResp.<List<UserPostComment>> builder()
            .syscode(SysCode.OK)
            .data(apiService.getUsers())
            .build();
    }

    // http://localhost:8005/jph/users/posts/comments
    @Override
    public ApiResp<List<UserPostCommentDTO>> getUsersPostsComments() {
        List<UserPostComment> users = apiService.getUsers();
        List<UserPostCommentDTO> userPostCommentDTO = UserPostCommentDTOMapper.map(users);
        return ApiResp.<List<UserPostCommentDTO>> builder()
            .syscode(SysCode.OK)
            .data(userPostCommentDTO)
            .build();
    } 

    // http://localhost:8005/jph/users/comments?userId=1
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

        List<UserPostComment> users = apiService.getUsers();
        boolean userExist = users.stream()
            .anyMatch(user -> user.getId().equals(userLongId));
        if (!userExist) {
            return ApiResp.<UserCommentDTO>builder()
            .syscode(SysCode.USER_NOT_FOUND)
            .build();
        }

        UserPostComment user = apiService.getUserComments(userLongId);
        UserCommentDTO userCommentDTO = userCommentDTOMapper.map(user);
        return ApiResp.<UserCommentDTO>builder()
            .syscode(SysCode.OK)
            .data(userCommentDTO)
            .build();
    }

}

