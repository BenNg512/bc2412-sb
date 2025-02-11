package com.example.restful.demo_restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//! ComponentScan -> search the whole project, check if any 
//! @Controller, @Service, @Repository, @Configuration, @Component is in this project
public class DemoSbRestfulApplication {

	public static ConfigurableApplicationContext context;
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
		SpringApplication.run(DemoSbRestfulApplication.class, args);
	}

}
