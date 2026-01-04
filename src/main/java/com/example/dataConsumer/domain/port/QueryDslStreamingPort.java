package com.example.dataConsumer.domain.port;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.streaming.RowConsumer;

public interface QueryDslStreamingPort {

    void streamActiveCustomers(RowConsumer<CustomerEntity> consumer) throws Exception;
}
