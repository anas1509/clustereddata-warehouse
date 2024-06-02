package com.FxDeals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.FxDeals" })
@EntityScan("com.FxDeals.model")
@EnableJpaRepositories(basePackages = "com.FxDeals.repo")
public class FxDealsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxDealsApplication.class, args);
	}

}
