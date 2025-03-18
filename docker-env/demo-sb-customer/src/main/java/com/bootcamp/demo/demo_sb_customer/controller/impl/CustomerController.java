package com.bootcamp.demo.demo_sb_customer.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.demo.demo_sb_customer.codewave.SysCode;
import com.bootcamp.demo.demo_sb_customer.controller.CustomerOperation;
import com.bootcamp.demo.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.demo.demo_sb_customer.service.CustomerService;

@RestController
public class CustomerController implements CustomerOperation {
  @Autowired
  private CustomerService customerService;

  @Override
  public ApiResp<List<CustomerEntity>> getCustomers() {
    List<CustomerEntity> customerEntities = this.customerService.getCustomers();
    return ApiResp.<List<CustomerEntity>>builder() //
        .syscode(SysCode.OK) //
        .data(customerEntities) //
        .build();
  }

  @Override
  public ApiResp<CustomerEntity> createCustomer(CustomerEntity customerEntity) {
    System.out.println("createCustomer: customerEntity=" + customerEntity);
    CustomerEntity serviceResult =
        this.customerService.createCustomer(customerEntity);
    return ApiResp.<CustomerEntity>builder() //
        .syscode(SysCode.OK) //
        .data(serviceResult) //
        .build();
  }

  @Override
  public List<CustomerEntity> getCustomerByJPQL(@RequestParam String name) {
    return this.customerService.getCustomersByJPQL(name);
  }

  @Override
  public List<CustomerEntity> getCustomerByNQ(@RequestParam String name) {
    return this.customerService.getCustomersByNQ(name);
  }
}
