package com.appbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@EnableJpaRepositories(basePackages= {"com.appbank.model.repository"})
@EntityScan(value = {"com.appbank.model.entity"})
@ComponentScan(value = {"com.appbank.controller.rest", "com.appbank.model.service", "com.appbank.controller.rest.exception", "com.appbank.config", "com.appbank"})
@SpringBootApplication
public class AppbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppbankApplication.class, args);
	}

}
