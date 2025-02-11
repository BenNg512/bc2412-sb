package com.bootcamp.customer.demo_sb_customer.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.customer.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.customer.demo_sb_customer.entity.OrderEntity;

public interface OrderOperation {
  
  // http://localhost:8100/orders?customer_id=4
  @PostMapping(value = "/orders")
  ApiResp<OrderEntity> createOrder(@RequestParam(value = "customer_id") Long customerId,
      @RequestBody OrderEntity orderEntity);
  
  @GetMapping(value = "/orders")
  ApiResp<List<OrderEntity>> getOrders(@RequestParam(value = "customer_id") Long customerId);
}
