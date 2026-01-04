package com.example.dataConsumer.querydsl.adapter;

import com.example.dataConsumer.domain.model.CustomerRecord;
import com.example.dataConsumer.domain.model.OrderLineRecord;
import com.example.dataConsumer.domain.port.QueryDslStreamingPort;
import com.example.dataConsumer.querydsl.QueryDslSqlStreamingExecutor;
import com.example.dataConsumer.querydsl.table.QCustomerRecord;
import com.example.dataConsumer.querydsl.table.QOrderLineRecord;
import com.example.dataConsumer.streaming.RowConsumer;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Component;

@Component
public class QueryDslStreamingAdapter implements QueryDslStreamingPort {

    private final QueryDslSqlStreamingExecutor streamingExecutor;
    private final QCustomerRecord customer = QCustomerRecord.customerRecord;
    private final QOrderLineRecord orderLine = QOrderLineRecord.orderLineRecord;

    public QueryDslStreamingAdapter(QueryDslSqlStreamingExecutor streamingExecutor) {
        this.streamingExecutor = streamingExecutor;
    }

    @Override
    public void streamCustomers(int fetchSize, RowConsumer<CustomerRecord> consumer) throws Exception {
        streamingExecutor.stream(fetchSize, () -> streamingExecutor
                .selectAll(customer)
                .get()
                .select(Projections.constructor(CustomerRecord.class,
                        customer.id,
                        customer.email,
                        customer.status,
                        customer.createdAt)), consumer);
    }

    @Override
    public void streamOrderLines(int fetchSize, RowConsumer<OrderLineRecord> consumer) throws Exception {
        streamingExecutor.stream(fetchSize, () -> streamingExecutor
                .selectAll(orderLine)
                .get()
                .select(Projections.constructor(OrderLineRecord.class,
                        orderLine.orderId,
                        orderLine.lineNo,
                        orderLine.sku,
                        orderLine.quantity,
                        orderLine.amount)), consumer);
    }
}
