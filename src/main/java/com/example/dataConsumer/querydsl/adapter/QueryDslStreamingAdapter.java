package com.example.dataConsumer.querydsl.adapter;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.port.QueryDslStreamingPort;
import com.example.dataConsumer.querydsl.schema.QCustomerEntity;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryDslStreamingAdapter implements QueryDslStreamingPort {

    private final SQLQueryFactory sqlQueryFactory;
    private final QCustomerEntity customer = QCustomerEntity.customer;

    public QueryDslStreamingAdapter(SQLQueryFactory sqlQueryFactory) {
        this.sqlQueryFactory = sqlQueryFactory;
    }

    @Override
    public List<CustomerEntity> findActiveCustomers() {
        return sqlQueryFactory.selectFrom(customer)
                .where(customer.status.eq("ACTIVE"))
                .fetch();
    }
}
