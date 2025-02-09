package com.bootcamp.demosb.demo_sb_helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value = "/api/v1")
public class Cat {
  @GetMapping(value = "/meow")
  public String meow2() {
    return "Meow!";
  }
  
}
