package com.bootcamp.sbex2.sb_ex2_jsonplaceholder.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.dto.CommentDTO;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.dto.PostDTO;
import com.bootcamp.sbex2.sb_ex2_jsonplaceholder.dto.UserDTO;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    private final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private final String COMMENTS_URL = "https://jsonplaceholder.typicode.com/comments";

    public List<UserDTO> getUsersWithPostsAndComments() {
        // Fetch users
        UserDTO[] usersArray = restTemplate.getForObject(USERS_URL, UserDTO[].class);
        List<UserDTO> users = Arrays.asList(usersArray);

        // Fetch posts
        PostDTO[] postsArray = restTemplate.getForObject(POSTS_URL, PostDTO[].class);
        List<PostDTO> posts = Arrays.asList(postsArray);

        // Fetch comments
        CommentDTO[] commentsArray = restTemplate.getForObject(COMMENTS_URL, CommentDTO[].class);
        List<CommentDTO> comments = Arrays.asList(commentsArray);

        // Map comments to posts
        posts.forEach(post -> post.setComments(
            comments.stream()
                .filter(comment -> comment.getPostId().equals(post.getId()))
                .collect(Collectors.toList())
        ));

        // Map posts to users
        users.forEach(user -> user.setPosts(
            posts.stream()
                .filter(post -> post.getUserId().equals(user.getId()))
                .collect(Collectors.toList())
        ));

        return users;
    }
}
