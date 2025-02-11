package com.bootcamp.customer.demo_sb_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.customer.demo_sb_customer.entity.CustomerEntity;

// JPA + Driver (PostgreSQL)

@Repository
//! Hibernate generates the implement class, which implements "CustomerRepository"
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
  // save()
  // saveAll()
  // findAll()
  // findById()
  // deleteById
  
}
