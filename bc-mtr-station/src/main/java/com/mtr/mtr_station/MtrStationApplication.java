package com.mtr.mtr_station;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MtrStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtrStationApplication.class, args);
	}

}
