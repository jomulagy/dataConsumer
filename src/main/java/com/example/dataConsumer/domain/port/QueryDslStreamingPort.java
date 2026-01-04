package com.example.dataConsumer.domain.port;

import com.example.dataConsumer.domain.model.CustomerEntity;

import java.util.List;

public interface QueryDslStreamingPort {

    List<CustomerEntity> findActiveCustomers();
}
