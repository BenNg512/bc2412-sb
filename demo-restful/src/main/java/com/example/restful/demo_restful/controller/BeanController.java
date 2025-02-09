package com.example.restful.demo_restful.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.restful.demo_restful.DemoSbRestfulApplication;

@RestController
// http://localhost:8082/beans
public class BeanController {
  @GetMapping(value = "/beans")
  public List <String> getBeans() {
    return Arrays.asList(DemoSbRestfulApplication.context.getBeanDefinitionNames());
  }
  
}
