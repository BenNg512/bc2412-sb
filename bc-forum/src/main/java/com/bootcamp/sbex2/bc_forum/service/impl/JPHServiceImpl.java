package com.bootcamp.sbex2.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.endpoint.ApiEndpoint;
import com.bootcamp.sbex2.bc_forum.service.JPHService;

@Service
public class JPHServiceImpl implements JPHService {

@Autowired
  private RestTemplate restTemplate;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;

  public List<UserDTO> getUsers() {
    String usersUrl = ApiEndpoint.USERS.getUrl(domain);
    String postsUrl = ApiEndpoint.POSTS.getUrl(domain);
    String commentsUrl = ApiEndpoint.COMMENTS.getUrl(domain);

    UserDTO[] usersArray = this.restTemplate.getForObject(usersUrl, UserDTO[].class);
      List<UserDTO> users = Arrays.asList(usersArray);
    UserDTO.Post[] postsArray = this.restTemplate.getForObject(postsUrl, UserDTO.Post[].class);
      List<UserDTO.Post> posts = Arrays.asList(postsArray);
    UserDTO.Post.Comment[] commentsArray = this.restTemplate.getForObject(commentsUrl, UserDTO.Post.Comment[].class);
      List<UserDTO.Post.Comment> comments = Arrays.asList(commentsArray);

    posts.forEach(post -> {
      List<UserDTO.Post.Comment> postComments = comments.stream()
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

  public UserDTO getUserComments(Long userId) {
    List<UserDTO> users = this.getUsers();
    UserDTO user = users.stream()
      .filter(u -> u.getId().equals(userId))
      .findFirst()
      .orElse(null);
    return user;
  }

}
