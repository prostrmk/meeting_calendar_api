package com.itechart.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class WebfluxTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxTestApplication.class, args);
    }

}
