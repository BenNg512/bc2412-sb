package com.bootcamp.customer.demo_sb_customer.model;

import java.time.LocalDate;
import com.bootcamp.customer.demo_sb_customer.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Order {
  private Double amount;
  private LocalDate orderDate;
  private CustomerEntity customerEntity;
}
