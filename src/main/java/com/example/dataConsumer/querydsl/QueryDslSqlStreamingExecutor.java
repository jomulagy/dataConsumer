package com.example.dataConsumer.querydsl;

import com.example.dataConsumer.streaming.RowConsumer;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.RelationalPath;
import java.util.Objects;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class QueryDslSqlStreamingExecutor {

    private static final int DEFAULT_FETCH_SIZE = 1000;

    private final SQLQueryFactory sqlQueryFactory;

    public QueryDslSqlStreamingExecutor(SQLQueryFactory sqlQueryFactory) {
        this.sqlQueryFactory = sqlQueryFactory;
    }

    @Transactional(readOnly = true)
    public <T> void stream(int fetchSize, Supplier<SQLQuery<T>> querySupplier, RowConsumer<T> consumer)
            throws Exception {
        Objects.requireNonNull(querySupplier, "querySupplier must not be null");
        Objects.requireNonNull(consumer, "consumer must not be null");
        SQLQuery<T> query = querySupplier.get();
        int effectiveFetchSize = fetchSize > 0 ? fetchSize : DEFAULT_FETCH_SIZE;
        query.getMetadata().setFetchSize(effectiveFetchSize);
        try (var iterator = query.iterate()) {
            while (iterator.hasNext()) {
                consumer.accept(iterator.next());
            }
        }
    }

    public <T> Supplier<SQLQuery<T>> selectAll(RelationalPath<T> relationalPath) {
        return () -> sqlQueryFactory.selectFrom(relationalPath);
    }
}
