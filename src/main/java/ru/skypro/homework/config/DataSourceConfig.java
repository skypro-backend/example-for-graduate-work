package ru.skypro.homework.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {


    @Bean
    @Primary
    DataSource dataSource(
            @Value("${app.datasource.driver-class-name}") String driver,
            @Value("${app.datasource.url}") String url,
            @Value("${app.datasource.username}") String username,
            @Value("${app.datasource.password}") String password
            ) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
