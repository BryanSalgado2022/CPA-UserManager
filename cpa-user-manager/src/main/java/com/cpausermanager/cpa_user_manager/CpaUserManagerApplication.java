package com.cpausermanager.cpa_user_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CpaUserManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpaUserManagerApplication.class, args);
	}

}
