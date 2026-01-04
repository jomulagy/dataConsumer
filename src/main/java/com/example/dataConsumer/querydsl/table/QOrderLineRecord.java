package com.example.dataConsumer.querydsl.table;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.example.dataConsumer.domain.model.OrderLineRecord;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.PrimaryKey;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SchemaAndTable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class QOrderLineRecord extends RelationalPathBase<OrderLineRecord> {

    public static final QOrderLineRecord orderLineRecord = new QOrderLineRecord("order_line");

    public final NumberPath<Long> orderId = createNumber("order_id", Long.class);
    public final NumberPath<Integer> lineNo = createNumber("line_no", Integer.class);
    public final StringPath sku = createString("sku");
    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);
    public final com.querydsl.core.types.dsl.NumberPath<BigDecimal> amount = createNumber("amount", BigDecimal.class);

    public final PrimaryKey<OrderLineRecord> pk = createPrimaryKey(orderId, lineNo);

    public QOrderLineRecord(String variable) {
        super(OrderLineRecord.class, forVariable(variable), null, "order_line");
    }

    public QOrderLineRecord(Path<? extends OrderLineRecord> path) {
        super(path.getType(), path.getMetadata(), null, "order_line");
    }

    public QOrderLineRecord(PathMetadata metadata) {
        super(OrderLineRecord.class, metadata, null, "order_line");
    }

    @Override
    public List<Path<?>> getPrimaryKeyFields() {
        return Arrays.asList(orderId, lineNo);
    }

    @Override
    public SchemaAndTable getSchemaAndTable() {
        return new SchemaAndTable(null, "order_line");
    }
}
