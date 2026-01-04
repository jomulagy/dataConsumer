package com.example.dataConsumer.mybatis;

import com.example.dataConsumer.streaming.RowConsumer;
import java.util.Objects;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyBatisStreamingExecutor {

    private static final int DEFAULT_FETCH_SIZE = 1000;

    private final SqlSessionTemplate sqlSessionTemplate;

    public MyBatisStreamingExecutor(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public <T> void stream(String statementId, Object parameter, Integer fetchSize, RowConsumer<T> consumer)
            throws Exception {
        Objects.requireNonNull(statementId, "statementId must not be null");
        Objects.requireNonNull(consumer, "consumer must not be null");
        int effectiveFetchSize = (fetchSize != null && fetchSize > 0) ? fetchSize : DEFAULT_FETCH_SIZE;
        PostgreSqlFetchSizeInterceptor.setFetchSize(effectiveFetchSize);
        try (Cursor<T> cursor = sqlSessionTemplate.selectCursor(statementId, parameter,
                new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT))) {
            for (T row : cursor) {
                consumer.accept(row);
            }
        } finally {
            PostgreSqlFetchSizeInterceptor.clear();
        }
    }
}
