package com.itechart.webflux.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication(scanBasePackages = "com.itechart.webflux.*")
@EnableJdbcRepositories
public class WebfluxTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxTestApplication.class, args);
    }

}
