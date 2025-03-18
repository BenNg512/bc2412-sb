package com.bootcamp.demo.demo_sb_customer.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_customer.controller.CalculatorOperation;
import com.bootcamp.demo.demo_sb_customer.dto.CalculatorResult;
import com.bootcamp.demo.demo_sb_customer.service.CalculatorService;

// @Autowried(required=false)
// CalculatorOperation calculatorOperation;
// then method will call calculatorOperation.xxxx()

@RestController
@RequestMapping(value = "/v2")
public class CalculatorControllerV2 implements CalculatorOperation {
  @Autowired
  private CalculatorService calculatorService;

  // Unit test for calculate: assume sum() return 100 and subtract 200 -> -100
  // (1, 2) -> 3 - (-1) -> expect 4 (OK)
  // (1, 2) -> 3 - (-2) -> expect 4 (Fail)
  @Override
  public CalculatorResult calculate(int x, int y) {
    return new CalculatorResult(
        calculatorService.sum(x, y) - calculatorService.subtract(x, y));
  }
}
