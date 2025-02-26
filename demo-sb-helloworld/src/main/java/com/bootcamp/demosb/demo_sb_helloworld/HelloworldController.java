package com.bootcamp.demosb.demo_sb_helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloworldController {
  @GetMapping(value = "/ipad/greeting")
  public String hello() {
    return "Hello World!+1+1+1+1";
  }

  @GetMapping(value = "/ipod/goodbye")
  public String bye() {
  return "Good Bye!";
  }

  @GetMapping(value = "/path/h")
  public String HB() {
  return "Happy Birthday!";
  }

  @GetMapping(value = "/path/c")
  public String MEOW() {
  return "Meow! Meow! Meow!";
  }
}
