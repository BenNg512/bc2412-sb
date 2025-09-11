package com.bootcamp.web.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.web.model.CoinDto;
import com.bootcamp.web.service.CoinService;


@Controller
public class ViewController {
@Autowired
private CoinService coinService;

  // http://localhost:8001/bootcamp
  @GetMapping(value = "/bootcamp")
  public String sayHelloPage(Model model) {
    model.addAttribute("tutor", "vincent");

    return "hello";
  } 

  // http://localhost:8001/coins
  @GetMapping(value = "/coins")
  public String coinPage(Model model) {
    List<CoinDto> coinDtos = coinService.getCoins();
    model.addAttribute("coin", coinDtos);

    ZonedDateTime apiFetchTime = ZonedDateTime.now();
    String formattedFetchTime = apiFetchTime.withZoneSameInstant(ZoneId.of("Asia/Hong_Kong"))
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'HKT'"));
    model.addAttribute("latestUpdate", formattedFetchTime);
    
    return "coin";
  }

}
