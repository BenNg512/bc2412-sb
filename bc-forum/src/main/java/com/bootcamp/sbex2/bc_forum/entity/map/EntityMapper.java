package com.bootcamp.sbex2.bc_forum.entity.map;

import org.springframework.stereotype.Component;
import com.bootcamp.sbex2.bc_forum.entity.AddressEntity;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.CompanyEntity;
import com.bootcamp.sbex2.bc_forum.entity.GeoEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;
import com.bootcamp.sbex2.bc_forum.model.dto.CommentDto;
import com.bootcamp.sbex2.bc_forum.model.dto.PostDto;
import com.bootcamp.sbex2.bc_forum.model.dto.UserDto;

@Component
public class EntityMapper {
  public CommentEntity map(CommentDto commentDto) {
    return CommentEntity.builder()
        .postId(commentDto.getPostId())
        .id(commentDto.getId())
        .name(commentDto.getName())
        .email(commentDto.getEmail())
        .body(commentDto.getBody())
        .build();
  }

  public PostEntity map(PostDto postDto){
    return PostEntity.builder()
        .userId(postDto.getUserId())
        .id(postDto.getId())
        .title(postDto.getTitle())
        .body(postDto.getBody())
        .build();
  }
  
    public UserEntity map(UserDto dto) {
      return UserEntity.builder()
        .id(dto.getId())
        .email(dto.getEmail())
        .name(dto.getName())
        .username(dto.getUsername())
        .website(dto.getWebsite())
        .phone(dto.getPhone())
        .build();
    }
  
    public AddressEntity map(UserDto.Address address) {
      return AddressEntity.builder()
        .street(address.getStreet())
        .city(address.getCity())
        .suite(address.getSuite())
        .zipcode(address.getZipcode())
        .build();
    }
  
    public CompanyEntity map(UserDto.Company company) {
      return CompanyEntity.builder()
        .catchPhrase(company.getCatchPhrase())
        .bs(company.getBs())
        .name(company.getName())
        .build();
    }
  
    public GeoEntity map(UserDto.Address.Geo geo) {
      return GeoEntity.builder()
        .latitude(Double.valueOf(geo.getLat()))
        .longitude(Double.valueOf(geo.getLng()))
        .build();
    }
}
