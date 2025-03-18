package com.bootcamp.demo.demo_sb_customer.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.demo.demo_sb_customer.codewave.SysCode;
import com.bootcamp.demo.demo_sb_customer.controller.OrderOperation;
import com.bootcamp.demo.demo_sb_customer.entity.OrderEntity;
import com.bootcamp.demo.demo_sb_customer.service.OrderService;

@RestController
public class OrderController implements OrderOperation {
  @Autowired
  private OrderService orderService;

  @Override
  public ApiResp<OrderEntity> createOrder(Long customerId,
      OrderEntity orderEntity) {
    OrderEntity serviceResult =
        this.orderService.createOrder(customerId, orderEntity);
    return ApiResp.<OrderEntity>builder().syscode(SysCode.OK)
        .data(serviceResult).build();
  }
}
