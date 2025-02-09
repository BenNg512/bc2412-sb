package com.example.restful.demo_restful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Cat {
  private Long id; //always use Long, don't use primitive since Long can be null
  private String name;
  private Integer age;
}
