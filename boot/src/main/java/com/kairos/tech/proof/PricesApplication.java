package com.kairos.tech.proof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.kairos.tech.proof")
public class PricesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricesApplication.class, args);
    }

}