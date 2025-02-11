package com.bootcamp.customer.demo_sb_customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.customer.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.customer.demo_sb_customer.entity.OrderEntity;
import com.bootcamp.customer.demo_sb_customer.model.BusinessException;
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
      .orElseThrow(() -> new BusinessException("Customer not found"));
      
      orderEntity.setCustomerEntity(customerEntity);
      // orderEntity to DB;
      return orderRepository.save(orderEntity);
  }
  
}
