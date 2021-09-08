package com.pedrospiet.msemail.adapters.configurations;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/ms-email");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("docker");

        return dataSourceBuilder.build();
    }
}
