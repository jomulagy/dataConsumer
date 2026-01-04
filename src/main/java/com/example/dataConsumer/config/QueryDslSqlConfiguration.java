package com.example.dataConsumer.config;

import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
public class QueryDslSqlConfiguration {

    @Bean
    public com.querydsl.sql.Configuration querydslConfiguration() {
        SQLTemplates templates = PostgreSQLTemplates.builder().printSchema().build();
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
        configuration.setExceptionTranslator(new SpringExceptionTranslator());
        return configuration;
    }

    @Bean
    public SQLQueryFactory sqlQueryFactory(DataSource dataSource, com.querydsl.sql.Configuration configuration) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource);
        return new SQLQueryFactory(configuration, new SpringConnectionProvider(proxy));
    }
}
