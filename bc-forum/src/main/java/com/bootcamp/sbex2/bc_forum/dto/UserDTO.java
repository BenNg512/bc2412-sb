package com.bootcamp.sbex2.bc_forum.dto;


import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;
  private List<Post> posts;

  @Getter
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    public static class Geo {
      private String lat;
      private String lng;
    }
  }
  
  @Getter
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }

  @Getter
  @Setter
  public static class Post {
    private Long userId;
    private Long id;
    private String title;
    private String body;
    private List<Comment> comments;

    @Getter
    @Setter
    public static class Comment {
      private Long postId;
      private Long id;
      private String name;
      private String email;
      private String body;
    }
  }
}