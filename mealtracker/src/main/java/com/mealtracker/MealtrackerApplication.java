package com.mealtracker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mealtracker.mapper")
public class MealtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealtrackerApplication.class, args);
	}

}
