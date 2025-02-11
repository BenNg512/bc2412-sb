package com.bootcamp.customer.demo_sb_customer.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.customer.demo_sb_customer.controller.OrderOperation;
import com.bootcamp.customer.demo_sb_customer.entity.OrderEntity;
import com.bootcamp.customer.demo_sb_customer.service.OrderService;

@RestController
public class OrderController implements OrderOperation {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderEntity createOrder(@RequestParam(value = "customer_id") Long customerId,
                                   @RequestBody OrderEntity orderEntity) {
        return orderService.createOrder(customerId, orderEntity);
    }
}