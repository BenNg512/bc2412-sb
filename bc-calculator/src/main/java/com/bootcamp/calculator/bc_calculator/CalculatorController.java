package com.bootcamp.calculator.bc_calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    // http://localhost:8083/v1/operation/2/7/sum
    @GetMapping(value = "/operation/{x}/{y}/{operation}")
    public String calculatorByPath(@PathVariable String x,
                              @PathVariable String y,
                              @PathVariable String operation) {
        return this.calculatorService.operate(x, y, operation);
    }

    // http://localhost:8083/v1/operation
    @PostMapping(value = "/operation")
    public Calculator calculatorByJson(@RequestBody Calculator calculator) {
      String result = this.calculatorService.operate(calculator.getX(), calculator.getY(), calculator.getOperation());
      return new Calculator (calculator.getX(), calculator.getY(), calculator.getOperation(), result);
    }

    // http://localhost:8083/v1/operation?x=2&y=7&operation=div
    @GetMapping(value = "/operation")
    public String calculatorByQuery(@RequestParam String x,
                                    @RequestParam String y,
                                    @RequestParam String operation) {
      String result = this.calculatorService.operate(x, y, operation);
      return result;
    }
}

