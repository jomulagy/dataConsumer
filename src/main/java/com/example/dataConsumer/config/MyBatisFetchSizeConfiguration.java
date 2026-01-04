package com.example.dataConsumer.config;

import com.example.dataConsumer.mybatis.PostgreSqlFetchSizeInterceptor;
import org.apache.ibatis.session.Configuration;
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
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.addInterceptor(interceptor);
            }
        };
    }
}
