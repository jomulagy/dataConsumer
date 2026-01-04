package com.example.dataConsumer.usecase;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.port.MyBatisStreamingPort;
import com.example.dataConsumer.domain.port.QueryDslStreamingPort;
import com.example.dataConsumer.streaming.RowConsumer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamingUseCase {

    private final MyBatisStreamingPort myBatisStreamingPort;
    private final QueryDslStreamingPort queryDslStreamingPort;

    public StreamingUseCase(MyBatisStreamingPort myBatisStreamingPort,
            QueryDslStreamingPort queryDslStreamingPort) {
        this.myBatisStreamingPort = myBatisStreamingPort;
        this.queryDslStreamingPort = queryDslStreamingPort;
    }

    public void executeUseCase() throws Exception {
        execute();
    }

    private void execute() {
        int fetchSize = 0;

        List<CustomerEntity> list = queryDslStreamingPort.findActiveCustomers();

        for(CustomerEntity customer : list) {
            System.out.println(customer.getId());
        }
    }
}
