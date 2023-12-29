package com.dsu_review_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = {"com.dsu_review_api.domain"})
@EnableJpaAuditing
@SpringBootApplication
public class DsuReviewApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsuReviewApiApplication.class, args);
	}

}
