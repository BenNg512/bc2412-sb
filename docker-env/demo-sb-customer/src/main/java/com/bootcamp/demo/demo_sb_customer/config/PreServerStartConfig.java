package com.bootcamp.demo.demo_sb_customer.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Autowired(required=false)
// CommandLineRunner commandLineRunner;

// instance method -> commandLineRunner.run();

@Component // bean
public class PreServerStartConfig implements CommandLineRunner {
  
  @Override
  public void run(String... args) throws Exception {
    // call JPH users api
    // call JPH post api
    // call JPH comment api
    // save DB
  }
}
