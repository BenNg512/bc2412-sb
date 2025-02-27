package com.bootcamp.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.web.model.CoinDto;
import com.bootcamp.web.service.CoinService;


@Controller
public class ViewController {
@Autowired
private CoinService coinService;

  // http://localhost:8082/bootcamp
  @GetMapping(value = "/bootcamp")
  public String sayHelloPage(Model model) {
    model.addAttribute("tutor", "vincent");

    return "hello"; // html file na
  }

  @GetMapping(value = "/coins")
  public String coinPage(Model model) {
    List<CoinDto> coinDtos = coinService.getCoins();
    model.addAttribute("coin", coinDtos);
    return "coin";
  }

}