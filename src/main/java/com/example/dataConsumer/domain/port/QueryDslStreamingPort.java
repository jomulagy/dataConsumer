package com.example.dataConsumer.domain.port;

import com.example.dataConsumer.domain.model.CustomerRecord;
import com.example.dataConsumer.domain.model.OrderLineRecord;
import com.example.dataConsumer.streaming.RowConsumer;

public interface QueryDslStreamingPort {

    void streamCustomers(int fetchSize, RowConsumer<CustomerRecord> consumer) throws Exception;

    void streamOrderLines(int fetchSize, RowConsumer<OrderLineRecord> consumer) throws Exception;
}
