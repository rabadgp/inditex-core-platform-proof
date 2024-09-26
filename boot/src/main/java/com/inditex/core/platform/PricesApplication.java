package com.inditex.core.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.inditex.core.platform")
//@EntityScan("com.inditex.core.platform.dataaccess.model")
//@EnableJpaRepositories(basePackages = "com.inditex.core.platform.dataaccess")
public class PricesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricesApplication.class, args);
    }

}