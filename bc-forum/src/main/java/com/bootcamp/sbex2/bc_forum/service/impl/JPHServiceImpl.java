package com.bootcamp.sbex2.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sbex2.bc_forum.lib.ApiEndpoint;
import com.bootcamp.sbex2.bc_forum.model.dto.UserPostComment;
import com.bootcamp.sbex2.bc_forum.service.JPHService;

@Service
public class JPHServiceImpl implements JPHService {

@Autowired
  private RestTemplate restTemplate;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  public List<UserPostComment> getUsers() {
    String usersUrl = ApiEndpoint.USERS.httpsBuilder(domain);
    String postsUrl = ApiEndpoint.POSTS.httpsBuilder(domain);
    String commentsUrl = ApiEndpoint.COMMENTS.httpsBuilder(domain);

    UserPostComment[] usersArray = 
      this.restTemplate.getForObject(usersUrl, UserPostComment[].class);
      List<UserPostComment> users = Arrays.asList(usersArray);
    UserPostComment.Post[] postsArray = 
      this.restTemplate.getForObject(postsUrl, UserPostComment.Post[].class);
      List<UserPostComment.Post> posts = Arrays.asList(postsArray);
    UserPostComment.Post.Comment[] commentsArray = 
      this.restTemplate.getForObject(commentsUrl, UserPostComment.Post.Comment[].class);
      List<UserPostComment.Post.Comment> comments = Arrays.asList(commentsArray);

    posts.forEach(post -> {
      List<UserPostComment.Post.Comment> postComments = comments.stream()
        .filter(comment -> comment.getPostId().equals(post.getId()))
        .collect(Collectors.toList());
        for (int i = 0; i < postComments.size(); i++) {
          postComments.get(i).setId((long) (i + 1));
        }
        post.setComments(postComments);
    });

    users.forEach(user -> user.setPosts(
        posts.stream()
        .filter(post -> post.getUserId().equals(user.getId()))
        .collect(Collectors.toList())
    ));
    return users;
  }

  public UserPostComment getUserComments(Long userId) {
    List<UserPostComment> users = this.getUsers();
    UserPostComment user = users.stream()
      .filter(u -> u.getId().equals(userId))
      .findFirst()
      .orElse(null);
    return user;
  }

}
