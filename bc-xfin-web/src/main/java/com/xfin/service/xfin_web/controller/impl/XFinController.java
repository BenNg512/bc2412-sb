package com.xfin.service.xfin_web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.xfin.service.xfin_web.controller.XFinOperation;
import com.xfin.service.xfin_web.model.FiveMinDataDto;
import com.xfin.service.xfin_web.service.impl.XFinServiceImpl;

@Controller //! not rest controller
public class XFinController implements XFinOperation {

@Autowired
private XFinServiceImpl xfinService;

  @Override
  public FiveMinDataDto getFiveMinData(String symbol) {
    return xfinService.getFiveMinData(symbol);
  }

  @Override
  public String stockPage(Model model, String symbol) {
    FiveMinDataDto data = xfinService.getFiveMinData(symbol);
    model.addAttribute("stockData", data.getDataMap().get(symbol).getData());
    model.addAttribute("symbol", symbol); 
    return "stock";
  }
  
}
