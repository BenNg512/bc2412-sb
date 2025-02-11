package com.bootcamp.customer.demo_sb_customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BusinessException extends RuntimeException {
  public BusinessException(String message) {
    super(message);
  }
  
}
