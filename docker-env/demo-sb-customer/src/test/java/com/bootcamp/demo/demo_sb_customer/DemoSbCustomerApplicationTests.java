package com.bootcamp.demo.demo_sb_customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// "mvn install" includes "mvn test"
// "mvn compile" -> ensures main code syntax alright.
// "mvn test-compile" -> ensures test code syntax alright
// "mvn test" -> execute test -> @SpringBootTest -> test once for the target testing env.
@SpringBootTest // simulate the actual Spring Bean Cycle.
class DemoSbCustomerApplicationTests {

  // ! test all dependency between components
  @Test
  void contextLoads() {

  }

}
