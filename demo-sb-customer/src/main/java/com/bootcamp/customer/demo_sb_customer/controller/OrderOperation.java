package com.bootcamp.customer.demo_sb_customer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.customer.demo_sb_customer.entity.OrderEntity;

public interface OrderOperation {
  
  // localhost:8100/order?cid=2
  @PostMapping(value = "/orders")
  OrderEntity createOrder(@RequestParam(value = "customer_id") Long customerId,
      @RequestBody OrderEntity orderEntity);
}
