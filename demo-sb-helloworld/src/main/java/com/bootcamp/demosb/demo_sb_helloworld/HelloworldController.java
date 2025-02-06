package com.bootcamp.demosb.demo_sb_helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HelloworldController {
  @GetMapping(value = "/greeting")
  public String hello() {
    return "Hello World!";
  }

  



}