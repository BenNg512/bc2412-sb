package com.bootcamp.sbex2.bc_forum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  @Builder
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Geo {
      private String lat;
      private String lng;
    }
  } 

  @Builder
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }

}
