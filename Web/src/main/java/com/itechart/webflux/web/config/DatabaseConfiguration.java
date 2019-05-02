package com.itechart.webflux.web.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import com.itechart.webflux.core.repository.UserRepository;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String DB_USERNAME;

    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;

    @Value("${spring.datasource.port}")
    private Integer DB_PORT;

    @Value("${spring.datasource.database}")
    private String DB_NAME;

    @Bean
    public PostgresqlConnectionFactory dbConfig() {
        PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration.builder()
                .host(DB_URL)
                .port(DB_PORT)
                .database(DB_NAME)
                .username(DB_USERNAME)
                .password(DB_PASSWORD)
                .build();
        return new PostgresqlConnectionFactory(config);
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory factory) {
        return DatabaseClient.builder().connectionFactory(factory).build();
    }

    @Bean
    public R2dbcRepositoryFactory factory(DatabaseClient client) {
        RelationalMappingContext context = new RelationalMappingContext();
        context.afterPropertiesSet();
        return new R2dbcRepositoryFactory(client, context);
    }

    @Bean
    public UserRepository userRepository(R2dbcRepositoryFactory factory) {
        return factory.getRepository(UserRepository.class);
    }

}
