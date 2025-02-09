package com.example.restful.demo_restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.restful.demo_restful.model.Operation;
import com.example.restful.demo_restful.service.CalculatorService;

//! sum 1+2=3
//! sub 2-1=1
//! mul 1*2=2
//! div 2/1=2

@RestController // => @Controller + @ResponseBody
// @Controller -> During server starts
@RequestMapping(value = "/v1")
public class CalculatorController {
  @Autowired
  private CalculatorService calculatorService;

  // http://localhost:8082/v1/operation/SUM?x=1&y=2
  @GetMapping(value = "/operation/{operation}")
  public Integer operate(@PathVariable Operation operation, 
      @RequestParam Integer x, @RequestParam Integer y) {
    return switch (operation) {
      case SUM -> x + y;
      case SUBTRACT -> x - y;
      case MULTIPLY -> x * y;
      case DIVIDE -> {
        // int result = y == 0? 0 : x / y;
        // yield result;
        int result;
        try {
          result = x / y;
        } catch (ArithmeticException e) {
          throw new RuntimeException(e);
        }
        yield result;
      }
    };
  }
}
