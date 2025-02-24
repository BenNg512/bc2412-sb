package com.bootcamp.goodbye.demo_goodbye;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class GoodByeController {
  @GetMapping(value = "/ipad/goodbye")
  public String hello() {
    return "Good Bye!2";
}
}
