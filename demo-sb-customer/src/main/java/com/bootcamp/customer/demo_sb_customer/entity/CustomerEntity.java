package com.bootcamp.customer.demo_sb_customer.entity;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "Customers")
@Getter
@Setter
public class CustomerEntity {
  @Id // PK
  @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment -> refer to id
  private Long id;

  @Column(name = "customer_name")
  private String name;

  @Column(name = "customer_age")
  private Integer age;

  @Column(name = "email")
  private String email;
  
  public Long findById() { // id is not created in constructor class so need to create this method
    return id;
  }

  @JsonIgnore
  @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private Set<OrderEntity> orders;
  
}
