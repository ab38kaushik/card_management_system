package com.example.card_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@EnableJpaRepositories(basePackages="com.example.dao")
@EntityScan(basePackages="com.example.model")
@ComponentScan(basePackages = {"com.example"})
@SpringBootApplication
public class CardManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardManagementSystemApplication.class, args);
    }

}
