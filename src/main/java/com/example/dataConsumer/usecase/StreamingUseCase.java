package com.example.dataConsumer.usecase;

import com.example.dataConsumer.mybatis.MyBatisStreamingExecutor;
import com.example.dataConsumer.querydsl.QueryDslSqlStreamingExecutor;
import com.example.dataConsumer.streaming.RowConsumer;
import com.querydsl.sql.SQLQuery;
import java.util.Map;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;

@Service
public class StreamingUseCase {

    private final MyBatisStreamingExecutor myBatisStreamingExecutor;
    private final QueryDslSqlStreamingExecutor queryDslSqlStreamingExecutor;

    public StreamingUseCase(MyBatisStreamingExecutor myBatisStreamingExecutor,
            QueryDslSqlStreamingExecutor queryDslSqlStreamingExecutor) {
        this.myBatisStreamingExecutor = myBatisStreamingExecutor;
        this.queryDslSqlStreamingExecutor = queryDslSqlStreamingExecutor;
    }

    public <T> void streamWithMyBatis(String statementId, Object parameter, int fetchSize, RowConsumer<T> consumer)
            throws Exception {
        myBatisStreamingExecutor.stream(statementId, parameter, fetchSize, consumer);
    }

    public <T> void streamWithQueryDsl(int fetchSize, Supplier<SQLQuery<T>> querySupplier, RowConsumer<T> consumer)
            throws Exception {
        queryDslSqlStreamingExecutor.stream(fetchSize, querySupplier, consumer);
    }

    public void runMyBatisExample(String statementId) throws Exception {
        streamWithMyBatis(statementId, Map.of(), 1000, row -> {
            // domain-specific processing logic goes here
        });
    }
}
