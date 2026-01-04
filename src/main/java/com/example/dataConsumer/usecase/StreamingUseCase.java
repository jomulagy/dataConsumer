package com.example.dataConsumer.usecase;

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

    public void executeUseCase(int fetchSize) throws Exception {
        execute(fetchSize);
    }

    private void execute(int fetchSize) throws Exception {
        var customerConsumer = RowConsumer.noop();
        var orderLineConsumer = RowConsumer.noop();

        myBatisStreamingPort.streamCustomers(fetchSize, customerConsumer);
        myBatisStreamingPort.streamOrderLines(fetchSize, orderLineConsumer);
        queryDslStreamingPort.streamCustomers(fetchSize, customerConsumer);
        queryDslStreamingPort.streamOrderLines(fetchSize, orderLineConsumer);
    }
}
