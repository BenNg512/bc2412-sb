package com.bootcamp.customer.demo_sb_customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.customer.demo_sb_customer.codewave.BusinessException;
import com.bootcamp.customer.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.customer.demo_sb_customer.entity.OrderEntity;
import com.bootcamp.customer.demo_sb_customer.codewave.SysCode;
import com.bootcamp.customer.demo_sb_customer.service.OrderService;
import com.bootcamp.customer.demo_sb_customer.repository.CustomerRepository;
import com.bootcamp.customer.demo_sb_customer.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private OrderRepository orderRepository;
  
  @Override
  public OrderEntity createOrder(Long CustomerId, OrderEntity orderEntity) {
    CustomerEntity customerEntity = customerRepository.findById(CustomerId)
      .orElseThrow(() -> BusinessException.of(SysCode.ID_NOT_FOUND));
      
      orderEntity.setCustomerEntity(customerEntity);
      // orderEntity to DB;
      return orderRepository.save(orderEntity);
  }
  @Override
  public List<OrderEntity> getOrders(Long customerId) {
    return null;
  }
  
}
