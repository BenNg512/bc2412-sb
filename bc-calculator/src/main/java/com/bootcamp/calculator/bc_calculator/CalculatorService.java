package com.bootcamp.calculator.bc_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
  public String operate(String x, String y, String operation) {
    Double X = Double.parseDouble(x);
    Double Y = Double.parseDouble(y);
    Operation op = Operation.valueOf(operation.toUpperCase());
    
    Double result = switch (op) {
      case SUM -> sum(X, Y);
      case SUB -> sub(X, Y);
      case MUL -> mul(X, Y);
      case DIV -> div(X, Y);
    };
    System.out.println("operate(): result=" + result);
    return Double.toString(result);
  }

  private Double sum(Double x, Double y) {
    return x + y;
  }

  private Double sub(Double x, Double y) {
    return x - y;
  }

  private Double mul(Double x, Double y) {
   return x * y;
  }

  private Double div(Double x, Double y) {
    if (y == 0) {
        throw new ArithmeticException();
    } else {
    Double result = BigDecimal.valueOf(x)
                    .divide(BigDecimal.valueOf(y), 5, RoundingMode.HALF_UP)
                    .doubleValue();
    return result;
    }
  }

}

