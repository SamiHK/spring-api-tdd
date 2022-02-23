package com.samiharoon.apitdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.samiharoon.apitdd.persistence"})
@EntityScan(basePackages = {"com.samiharoon.apitdd.persistence"})
@EnableJpaAuditing
public class ApiTddApplication {

	

	@PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

	public static void main(String[] args) {
		SpringApplication.run(ApiTddApplication.class, args);
	}

}
