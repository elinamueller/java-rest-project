package com.project.RestAPI2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.project.RestAPI2")
@EntityScan("com.project.RestAPI2.entity")
@EnableJpaRepositories("com.project.RestAPI2.repository")
public class RestApi2Application {

	public static void main(String[] args) {
		SpringApplication.run(RestApi2Application.class, args);
	}

}
