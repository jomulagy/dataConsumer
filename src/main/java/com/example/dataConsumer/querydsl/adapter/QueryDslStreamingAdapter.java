package com.example.dataConsumer.querydsl.adapter;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.port.QueryDslStreamingPort;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.dataConsumer.domain.model.QCustomerEntity.customerEntity;

@Component
@RequiredArgsConstructor
public class QueryDslStreamingAdapter implements QueryDslStreamingPort {

    private final JPAQueryFactory sqlQueryFactory;

    @Override
    public List<CustomerEntity> findActiveCustomers() {
        return sqlQueryFactory.selectFrom(customerEntity)
                .where(customerEntity.status.eq("ACTIVE"))
                .fetch();
    }
}
