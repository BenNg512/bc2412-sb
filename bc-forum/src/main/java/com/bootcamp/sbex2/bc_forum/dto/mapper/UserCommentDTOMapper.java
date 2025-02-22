package com.bootcamp.sbex2.bc_forum.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.bootcamp.sbex2.bc_forum.dto.UserCommentDTO;
import com.bootcamp.sbex2.bc_forum.dto.UserCommentDTO.Comment;
import com.bootcamp.sbex2.bc_forum.model.dto.UserPostComment;

@Component
public class UserCommentDTOMapper {

    public UserCommentDTO map(UserPostComment userDTO) {
        return UserCommentDTO.builder()
            .id(userDTO.getId())
            .username(userDTO.getUsername())
            .comments(toCommentList(userDTO.getPosts()))
            .build();
    }

    private static List<Comment> toCommentList(List<UserPostComment.Post> posts) {
        return posts.stream()
            .flatMap(post -> post.getComments().stream())
            .map(UserCommentDTOMapper::toComment)
            .collect(Collectors.toList());
    }

    private static Comment toComment(UserPostComment.Post.Comment commentDTO) {
        return Comment.builder()
            .name(commentDTO.getName())
            .email(commentDTO.getEmail())
            .body(commentDTO.getBody())
            .build();
    }
}
