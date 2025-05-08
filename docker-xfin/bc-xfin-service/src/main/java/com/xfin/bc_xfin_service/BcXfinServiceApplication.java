package com.xfin.bc_xfin_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BcXfinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcXfinServiceApplication.class, args);
	}

}
