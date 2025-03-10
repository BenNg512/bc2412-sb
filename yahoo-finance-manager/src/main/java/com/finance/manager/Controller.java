package com.finance.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.finance.manager.model.QuoteDto;

@RestController
public class Controller {
@Autowired
CrumbManager crumbManager;
@Autowired
YahooFinanceManager financeManager;

  // http://localhost:8080/crumb
  // get crumb from https://query1.finance.yahoo.com/v1/test/getcrumb
  @GetMapping("/crumb")
  public String getCrumb(){
    return crumbManager.getCrumb();
  }

  @GetMapping("/quote")
  public QuoteDto getApi(@RequestParam String code) {
    return financeManager.getQuote(code);
  }
}
