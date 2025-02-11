package com.bootcamp.customer.demo_sb_customer.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.customer.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.customer.demo_sb_customer.codewave.SysCode;
import com.bootcamp.customer.demo_sb_customer.controller.OrderOperation;
import com.bootcamp.customer.demo_sb_customer.entity.OrderEntity;
import com.bootcamp.customer.demo_sb_customer.service.OrderService;

@RestController
public class OrderController implements OrderOperation {

    @Autowired
    private OrderService orderService;

    @Override
    public ApiResp<OrderEntity> createOrder(@RequestParam(value = "customer_id") Long customerId,
                                            @RequestBody OrderEntity orderEntity) {
        OrderEntity order = orderService.createOrder(customerId, orderEntity);
        return ApiResp.<OrderEntity>builder().sysCode(SysCode.OK).data(order).build(); // <1>
    }

    @Override
    public ApiResp<List<OrderEntity>> getOrders(@RequestParam(value = "customer_id") Long customerId) {
        List<OrderEntity> orders = orderService.getOrders(customerId);
        return ApiResp.<List<OrderEntity>>builder().sysCode(SysCode.OK).data(orders).build();
    }
}