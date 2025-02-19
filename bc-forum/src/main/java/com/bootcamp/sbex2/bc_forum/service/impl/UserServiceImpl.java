package com.bootcamp.sbex2.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bootcamp.sbex2.bc_forum.endpoint.ApiEndpoint;
import com.bootcamp.sbex2.bc_forum.entity.AddressEntity;
import com.bootcamp.sbex2.bc_forum.entity.CompanyEntity;
import com.bootcamp.sbex2.bc_forum.entity.GeoEntity;
import com.bootcamp.sbex2.bc_forum.entity.UserEntity;
import com.bootcamp.sbex2.bc_forum.entity.map.EntityMapper;
import com.bootcamp.sbex2.bc_forum.model.dto.UserDto;
import com.bootcamp.sbex2.bc_forum.repository.AddressRepository;
import com.bootcamp.sbex2.bc_forum.repository.CompanyRepository;
import com.bootcamp.sbex2.bc_forum.repository.GeoRepository;
import com.bootcamp.sbex2.bc_forum.repository.UserRepository;
import com.bootcamp.sbex2.bc_forum.service.UserService;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private CompanyRepository companyRepository;
  @Autowired
  private GeoRepository geoRepository;
  @Autowired
  private EntityMapper entityMapper;

  @Value("${api.jsonplaceholder.domain}")
  private String domain;
  @Value("${api.jsonplaceholder.endpoints.users}")
  private String usersEndpoint;

  @Override
  public List<UserDto> getAllUsers() {
    String url = ApiEndpoint.USERS.getUrl(domain);

    UserDto[] results = this.restTemplate.getForObject(url, UserDto[].class);

    return Arrays.asList(results);
  }
  @Override
  public List<UserDto> fetchAndSaveAllUsers(){
    List<UserDto> userDtos = this.getAllUsers();
    // Clear DB
    this.geoRepository.deleteAll();
    this.addressRepository.deleteAll();
    this.companyRepository.deleteAll();
    this.userRepository.deleteAll();
    
    // Save DB (procedures)
    userDtos.stream().forEach(e -> {
      UserEntity userEntity = this.userRepository.save(this.entityMapper.map(e));

      AddressEntity addressEntity = this.entityMapper.map(e.getAddress());
      addressEntity.setUserEntity(userEntity);
      this.addressRepository.save(addressEntity);

      CompanyEntity companyEntity = this.entityMapper.map(e.getCompany());
      companyEntity.setUserEntity(userEntity);
      this.companyRepository.save(companyEntity);

      GeoEntity geoEntity = this.entityMapper.map(e.getAddress().getGeo());
      geoEntity.setAddressEntity(addressEntity);
      this.geoRepository.save(geoEntity);
    });
    return userDtos;
  }
}
