package com.example.dataConsumer.domain.port;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.model.OrderLineEntity;
import com.example.dataConsumer.streaming.RowConsumer;

public interface QueryDslStreamingPort {

    void streamCustomers(int fetchSize, RowConsumer<CustomerEntity> consumer) throws Exception;

    void streamOrderLines(int fetchSize, RowConsumer<OrderLineEntity> consumer) throws Exception;
}
