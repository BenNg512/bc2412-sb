package com.bootcamp.demo.demo_sb_customer.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.demo_sb_customer.codewave.ApiResp;
import com.bootcamp.demo.demo_sb_customer.codewave.SysCode;
import com.bootcamp.demo.demo_sb_customer.controller.CustomerOperation;
import com.bootcamp.demo.demo_sb_customer.entity.CustomerEntity;
import com.bootcamp.demo.demo_sb_customer.repository.CustomerRepository;
import com.bootcamp.demo.demo_sb_customer.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CustomerController implements CustomerOperation {
  @Autowired
  private CustomerService customerService;
  @Autowired
  CustomerRepository customerRepository;

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
  // http://localhost:8100/findBen
  @GetMapping("/findBen")
  public List<CustomerEntity> findBen(){
    return this.customerRepository.findByNameByJPQL("Ben");
  }

  // http://localhost:8100/findCustomer?name=Ben
  @GetMapping("/findCustomer")
  public List<CustomerEntity> getName(@RequestParam String name){
    return this.customerRepository.findByNameByNativeQuery(name);
  }

}
