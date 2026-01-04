package com.example.dataConsumer.mybatis.adapter;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.example.dataConsumer.domain.model.OrderLineEntity;
import com.example.dataConsumer.domain.port.MyBatisStreamingPort;
import com.example.dataConsumer.mybatis.MyBatisStreamingExecutor;
import com.example.dataConsumer.streaming.RowConsumer;
import org.springframework.stereotype.Component;

@Component
public class MyBatisStreamingAdapter implements MyBatisStreamingPort {

    private static final String CUSTOMER_STATEMENT = "com.example.dataConsumer.mybatis.mapper.StreamingTestMapper.selectCustomersForStreaming";
    private static final String ORDER_LINE_STATEMENT = "com.example.dataConsumer.mybatis.mapper.StreamingTestMapper.selectOrderLinesForStreaming";

    private final MyBatisStreamingExecutor streamingExecutor;

    public MyBatisStreamingAdapter(MyBatisStreamingExecutor streamingExecutor) {
        this.streamingExecutor = streamingExecutor;
    }

    @Override
    public void streamCustomers(int fetchSize, RowConsumer<CustomerEntity> consumer) throws Exception {
        streamingExecutor.stream(CUSTOMER_STATEMENT, null, fetchSize, consumer);
    }

    @Override
    public void streamOrderLines(int fetchSize, RowConsumer<OrderLineEntity> consumer) throws Exception {
        streamingExecutor.stream(ORDER_LINE_STATEMENT, null, fetchSize, consumer);
    }
}
