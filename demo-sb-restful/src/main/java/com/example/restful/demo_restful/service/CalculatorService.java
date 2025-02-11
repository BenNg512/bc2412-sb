package com.example.restful.demo_restful.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.restful.demo_restful.exception.BusinessException;
import com.example.restful.demo_restful.model.Operation;

@Service
public class CalculatorService {
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
          result = 0;
        }
        yield result;
      }
    };
  }
  private Integer divide(Integer x, Integer y) {
    if (y == 0) 
      throw new BusinessException(
        String.format("Cannot divide %d by zero", x)
      );
    
    return x / y;
  }
}
