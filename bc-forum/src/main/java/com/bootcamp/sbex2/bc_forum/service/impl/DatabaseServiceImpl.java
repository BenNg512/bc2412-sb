package com.bootcamp.sbex2.bc_forum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.sbex2.bc_forum.dto.UserDTO;
import com.bootcamp.sbex2.bc_forum.entity.AddressEntity;
import com.bootcamp.sbex2.bc_forum.entity.CommentEntity;
import com.bootcamp.sbex2.bc_forum.entity.CompanyEntity;
import com.bootcamp.sbex2.bc_forum.entity.GeoEntity;
import com.bootcamp.sbex2.bc_forum.entity.PostEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;
import com.bootcamp.sbex2.bc_forum.entity.map.EntityMapper;
import com.bootcamp.sbex2.bc_forum.repository.AddressRepository;
import com.bootcamp.sbex2.bc_forum.repository.CommentRepository;
import com.bootcamp.sbex2.bc_forum.repository.CompanyRepository;
import com.bootcamp.sbex2.bc_forum.repository.GeoRepository;
import com.bootcamp.sbex2.bc_forum.repository.PostRepository;
import com.bootcamp.sbex2.bc_forum.repository.UserRepository;
import com.bootcamp.sbex2.bc_forum.service.DatabaseService;

@Service
public class DatabaseServiceImpl implements DatabaseService {
  @Autowired
  CommentRepository commentRepository;
  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  AddressRepository addressRepository;
  @Autowired
  GeoRepository geoRepository;
  @Autowired
  CompanyRepository companyRepository;
  @Autowired
  EntityMapper entityMapper;
  

  public List<CommentEntity> getAllComments(){
      return commentRepository.findAllByOrderByIdAsc();
    }
  public List<PostEntity> getAllPosts(){
      return postRepository.findAll();
    }
  public List<UserEntity> getAllUsers(){
      return userRepository.findAll();
    }

  @Override
  public List<UserDTO> getAllUserDTOs(){
    List<UserEntity> userEntities = this.userRepository.findAll();
    List<AddressEntity> addressEntities = this.addressRepository.findAll();
    List<CompanyEntity> companyEntities = this.companyRepository.findAll();
    List<GeoEntity> geoEntities = this.geoRepository.findAll();

      return this.entityMapper.map(
        userEntities, 
        addressEntities, 
        companyEntities, 
        geoEntities);
  }
  
}
