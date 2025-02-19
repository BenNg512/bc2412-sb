package com.bootcamp.sbex2.bc_forum.model.dto;

import lombok.Getter;

@Getter
public class UserDto {
  private Long id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  @Getter
  public class Address{
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    public class Geo{
      private String lat;
      private String lng;
      public String getLongitude() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLongitude'");
      }
    }
  }
  
  @Getter
  public class Company{
    private String name;
    private String catchPhrase;
    private String bs;
  }
}
