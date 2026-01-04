package com.example.dataConsumer.usecase;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.model.OrderLineEntity;
import com.example.dataConsumer.domain.port.MyBatisStreamingPort;
import com.example.dataConsumer.domain.port.QueryDslStreamingPort;
import com.example.dataConsumer.streaming.RowConsumer;
import org.springframework.stereotype.Service;

@Service
public class StreamingUseCase {

    private final MyBatisStreamingPort myBatisStreamingPort;
    private final QueryDslStreamingPort queryDslStreamingPort;

    public StreamingUseCase(MyBatisStreamingPort myBatisStreamingPort,
            QueryDslStreamingPort queryDslStreamingPort) {
        this.myBatisStreamingPort = myBatisStreamingPort;
        this.queryDslStreamingPort = queryDslStreamingPort;
    }

    public void executeUseCase(int fetchSize, RowConsumer<CustomerEntity> customerConsumer,
            RowConsumer<OrderLineEntity> orderLineConsumer) throws Exception {
        streamMyBatisCustomers(fetchSize, customerConsumer);
        streamMyBatisOrderLines(fetchSize, orderLineConsumer);
        streamQueryDslCustomers(fetchSize, customerConsumer);
        streamQueryDslOrderLines(fetchSize, orderLineConsumer);
    }

    public void streamMyBatisCustomers(int fetchSize, RowConsumer<CustomerEntity> consumer) throws Exception {
        myBatisStreamingPort.streamCustomers(fetchSize, consumer);
    }

    public void streamMyBatisOrderLines(int fetchSize, RowConsumer<OrderLineEntity> consumer) throws Exception {
        myBatisStreamingPort.streamOrderLines(fetchSize, consumer);
    }

    public void streamQueryDslCustomers(int fetchSize, RowConsumer<CustomerEntity> consumer) throws Exception {
        queryDslStreamingPort.streamCustomers(fetchSize, consumer);
    }

    public void streamQueryDslOrderLines(int fetchSize, RowConsumer<OrderLineEntity> consumer) throws Exception {
        queryDslStreamingPort.streamOrderLines(fetchSize, consumer);
    }
}
