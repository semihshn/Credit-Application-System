package com.payten.creditsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CreditSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditSystemApplication.class, args);
    }

}
