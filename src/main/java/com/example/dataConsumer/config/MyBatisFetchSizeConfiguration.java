package com.example.dataConsumer.config;

import com.example.dataConsumer.mybatis.PostgreSqlFetchSizeInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisFetchSizeConfiguration {

    @Bean
    public PostgreSqlFetchSizeInterceptor postgreSqlFetchSizeInterceptor() {
        return new PostgreSqlFetchSizeInterceptor();
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer(PostgreSqlFetchSizeInterceptor interceptor) {
        return configuration -> {
            configuration.setDefaultFetchSize(1000);
        };
    }
}
