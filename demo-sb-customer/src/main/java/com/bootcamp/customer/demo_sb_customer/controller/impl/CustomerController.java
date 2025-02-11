package com.bootcamp.customer.demo_sb_customer.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.customer.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.customer.demo_sb_customer.codewave.SysCode;
import com.bootcamp.customer.demo_sb_customer.controller.CustomerOperation;
import com.bootcamp.customer.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.customer.demo_sb_customer.service.CustomerService;

@RestController
public class CustomerController implements CustomerOperation {
  @Autowired
  private CustomerService customerService;

  @Override
  public ApiResp<List<CustomerEntity>> getCustomers() {
    List<CustomerEntity> customers = this.customerService.getCustomers();
    return ApiResp.<List<CustomerEntity>>builder() //
          .sysCode(SysCode.OK) //
          .data(customers) //
          .build();
  }

  @Override
  public ApiResp<CustomerEntity> createCustomer(CustomerEntity customerEntity) {
    CustomerEntity ServiceResult = this.customerService.createCustomer(customerEntity);
    return ApiResp.<CustomerEntity>builder() //
          .sysCode(SysCode.OK) //
          .data(ServiceResult) //
          .build();
  }
}
