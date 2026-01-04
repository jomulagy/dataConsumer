package com.example.dataConsumer.querydsl.adapter;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.port.QueryDslStreamingPort;
import com.example.dataConsumer.streaming.RowConsumer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.example.dataConsumer.domain.model.QCustomerEntity.customerEntity;

@Component
@RequiredArgsConstructor
public class QueryDslStreamingAdapter implements QueryDslStreamingPort {

    private final JPAQueryFactory sqlQueryFactory;

    @Override
    public void streamActiveCustomers(RowConsumer<CustomerEntity> consumer) throws Exception {
        try (Stream<CustomerEntity> stream = sqlQueryFactory.selectFrom(customerEntity)
                .where(customerEntity.status.eq("ACTIVE"))
                .stream()) {

            Iterator<CustomerEntity> iterator = stream.iterator();
            while (iterator.hasNext()) {
                consumer.accept(iterator.next());
            }
        }
    }
}
