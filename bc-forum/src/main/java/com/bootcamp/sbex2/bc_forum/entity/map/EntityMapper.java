package com.bootcamp.sbex2.bc_forum.entity.map;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
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

    // public UserDTO map(
    //     UserEntity userEntity, 
    //     AddressEntity addressEntity, 
    //     CompanyEntity companyEntity, 
    //     GeoEntity geoEntity) {

    //   return UserDTO.builder()
    //     .id(userEntity.getId())
    //     .email(userEntity.getEmail())
    //     .name(userEntity.getName())
    //     .username(userEntity.getUsername())
    //     .website(userEntity.getWebsite())
    //     .phone(userEntity.getPhone())
    //     .address(UserDTO.Address.builder()
    //       .street(addressEntity.getStreet())
    //       .city(addressEntity.getCity())
    //       .suite(addressEntity.getSuite())
    //       .zipcode(addressEntity.getZipcode())
    //       .geo(UserDTO.Address.Geo.builder()
    //         .lat(geoEntity.getLatitude().toString())
    //         .lng(geoEntity.getLongitude().toString())
    //         .build())
    //       .build())
    //     .company(UserDTO.Company.builder()
    //       .name(companyEntity.getName())
    //       .catchPhrase(companyEntity.getCatchPhrase())
    //       .bs(companyEntity.getBs())
    //       .build())
    //     .build();
    // }

    // public UserDTO map(
    //     UserEntity userEntity, 
    //     AddressEntity addressEntity, 
    //     CompanyEntity companyEntity, 
    //     GeoEntity geoEntity) {

    public List<UserDTO> map(
      List<UserEntity> userEntity, 
      List<AddressEntity> addressEntity, 
      List<CompanyEntity> companyEntity, 
      List<GeoEntity> geoEntity) {



      return userEntity.stream()
        .map(e -> UserDTO.builder()
          .id(e.getId())
          .email(e.getEmail())
          .name(e.getName())
          .username(e.getUsername())
          .website(e.getWebsite())
          .phone(e.getPhone())
          .address(UserDTO.Address.builder()
            .street(addressEntity.get(0).getStreet())
            .city(addressEntity.get(0).getCity())
            .suite(addressEntity.get(0).getSuite())
            .zipcode(addressEntity.get(0).getZipcode())
            .geo(UserDTO.Address.Geo.builder()
              .lat(geoEntity.get(0).getLatitude().toString())
              .lng(geoEntity.get(0).getLongitude().toString())
              .build())
            .build())
          .company(UserDTO.Company.builder()
            .name(companyEntity.get(0).getName())
            .catchPhrase(companyEntity.get(0).getCatchPhrase())
            .bs(companyEntity.get(0).getBs())
            .build())
          .build())
        .collect(Collectors.toList());
    }
}
