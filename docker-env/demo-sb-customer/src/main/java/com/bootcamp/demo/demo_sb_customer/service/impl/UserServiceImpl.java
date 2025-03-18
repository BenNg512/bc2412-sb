package com.bootcamp.demo.demo_sb_customer.service.impl;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.demo_sb_customer.codewave.RedisManager;
import com.bootcamp.demo.demo_sb_customer.entity.AddressEntity;
import com.bootcamp.demo.demo_sb_customer.entity.CompanyEntity;
import com.bootcamp.demo.demo_sb_customer.entity.GeoEntity;
import com.bootcamp.demo.demo_sb_customer.entity.UserEntity;
import com.bootcamp.demo.demo_sb_customer.entity.mapper.EntityMapper;
import com.bootcamp.demo.demo_sb_customer.model.dto.UserDto;
import com.bootcamp.demo.demo_sb_customer.repository.AddressRepository;
import com.bootcamp.demo.demo_sb_customer.repository.CompanyRepository;
import com.bootcamp.demo.demo_sb_customer.repository.GeoRepository;
import com.bootcamp.demo.demo_sb_customer.repository.UserRepository;
import com.bootcamp.demo.demo_sb_customer.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

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

  @Autowired
  // private RedisTemplate<String, String> redisTemplate;
  private RedisManager redisManager;

  @Override
  public List<UserDto> getUsers() throws JsonProcessingException {
    // Cache Pattern: Read Through
    // 1. Read Redis first, if found, return users
    // String json = this.redisTemplate.opsForValue().get("jph-users");
    UserDto[] redisData = this.redisManager.get("jph-users", UserDto[].class);
    // "[{},{},{}]" -> Java Object (Deserialization)
    // ObjectMapper objectMapper = new ObjectMapper();
    if (redisData != null) {
      // UserDto[] userDtos = objectMapper.readValue(json, UserDto[].class);
      return Arrays.asList(redisData);
    }

    // 2. if not found, call JPH
    // String url = "https://jsonplaceholder.typicode.com/users";
    String url = UriComponentsBuilder.newInstance().scheme("https").host(domain)
        .path(usersEndpoint).build().toUriString();
    System.out.println("url=" + url);

    List<UserDto> userDtos =
        Arrays.asList(this.restTemplate.getForObject(url, UserDto[].class));
    // Clear DB
    this.geoRepository.deleteAll();
    this.addressRepository.deleteAll();
    this.companyRepository.deleteAll();
    this.userRepository.deleteAll();

    // Save DB (procedures)
    userDtos.stream().forEach(e -> {
      UserEntity userEntity =
          this.userRepository.save(this.entityMapper.map(e));

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
    // 3. Write the users back to redis
    // Java Object -> "[{},{},{}]" (Serialization)
    // String serializedJson = objectMapper.writeValueAsString(userDtos);
    // this.redisTemplate.opsForValue().set("jph-users", serializedJson, Duration.ofMinutes(1));
    this.redisManager.set("jph-users", userDtos, Duration.ofMinutes(1));
    return userDtos;
  }
}
