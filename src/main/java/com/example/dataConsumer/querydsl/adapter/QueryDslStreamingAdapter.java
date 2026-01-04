package com.example.dataConsumer.querydsl.adapter;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.model.OrderLineEntity;
import com.example.dataConsumer.domain.port.QueryDslStreamingPort;
import com.example.dataConsumer.querydsl.QueryDslSqlStreamingExecutor;
import com.example.dataConsumer.querydsl.schema.QCustomerEntity;
import com.example.dataConsumer.querydsl.schema.QOrderLineEntity;
import com.example.dataConsumer.streaming.RowConsumer;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Component;

@Component
public class QueryDslStreamingAdapter implements QueryDslStreamingPort {

    private final QueryDslSqlStreamingExecutor streamingExecutor;
    private final QCustomerEntity customer = QCustomerEntity.customer;
    private final QOrderLineEntity orderLine = QOrderLineEntity.orderLine;

    public QueryDslStreamingAdapter(QueryDslSqlStreamingExecutor streamingExecutor) {
        this.streamingExecutor = streamingExecutor;
    }

    @Override
    public void streamCustomers(int fetchSize, RowConsumer<CustomerEntity> consumer) throws Exception {
        streamingExecutor.stream(fetchSize, () -> streamingExecutor
                .selectAll(customer)
                .get()
                .select(Projections.constructor(CustomerEntity.class,
                        customer.id,
                        customer.email,
                        customer.status,
                        customer.createdAt)), consumer);
    }

    @Override
    public void streamOrderLines(int fetchSize, RowConsumer<OrderLineEntity> consumer) throws Exception {
        streamingExecutor.stream(fetchSize, () -> streamingExecutor
                .selectAll(orderLine)
                .get()
                .select(Projections.constructor(OrderLineEntity.class,
                        orderLine.orderId,
                        orderLine.lineNo,
                        orderLine.sku,
                        orderLine.quantity,
                        orderLine.amount)), consumer);
    }
}
