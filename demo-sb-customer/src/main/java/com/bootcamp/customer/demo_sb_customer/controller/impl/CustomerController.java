package com.bootcamp.customer.demo_sb_customer.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.customer.demo_sb_customer.controller.CustomerOperation;
import com.bootcamp.customer.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.customer.demo_sb_customer.service.CustomerService;

@RestController
public class CustomerController implements CustomerOperation {
  @Autowired
  private CustomerService customerService;

  @Override
  public List<CustomerEntity> getCustomers() {
    return this.customerService.getCustomers();
  }

  @Override
  public CustomerEntity createCustomer(CustomerEntity customerEntity) {
    return this.customerService.createCustomer(customerEntity);
  }
}
