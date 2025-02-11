package com.bootcamp.customer.demo_sb_customer.config;

import java.math.BigDecimal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //bean
public class AppConfig {
  //! More than 1 methods for creating Bean
  @Bean
  BigDecimal bigDecimal() {
    return BigDecimal.valueOf(10);
  }

  @Bean
  String tutor(){
    return "tutor";
  }

  // error for duplicated methods in bean
  // @Bean
  // String tutor2(){
  // return "tutor2";
  // }

}
