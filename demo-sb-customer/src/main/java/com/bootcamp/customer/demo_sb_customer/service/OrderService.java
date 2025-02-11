package com.bootcamp.customer.demo_sb_customer.service;

import com.bootcamp.customer.demo_sb_customer.entity.OrderEntity;

public interface OrderService {
  OrderEntity createOrder(Long CustomerId, OrderEntity orderEntity);
}
