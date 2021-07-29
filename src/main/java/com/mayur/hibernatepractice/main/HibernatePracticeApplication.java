package com.mayur.hibernatepractice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.mayur.hibernatepractice.entities")
@EnableJpaRepositories("com.mayur.hibernatepractice.repos")
@ComponentScan(basePackages = {"com.mayur.hibernatepractice.controllers", "com.mayur.hibernatepractice.services", "com.mayur.hibernatepractice.repos"})
public class HibernatePracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernatePracticeApplication.class, args);
	}

}
