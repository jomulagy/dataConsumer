package com.example.dataConsumer.querydsl.schema;

import com.example.dataConsumer.domain.model.OrderLineEntity;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.PrimaryKey;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.core.types.PathMetadataFactory;

public class QOrderLineEntity extends RelationalPathBase<OrderLineEntity> {

    public static final QOrderLineEntity orderLine = new QOrderLineEntity("order_line");

    public final NumberPath<Long> orderId = createNumber("order_id", Long.class);
    public final NumberPath<Integer> lineNo = createNumber("line_no", Integer.class);
    public final StringPath sku = createString("sku");
    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);
    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final PrimaryKey<OrderLineEntity> orderLinePkey = createPrimaryKey(orderId, lineNo);

    public QOrderLineEntity(String variable) {
        super(OrderLineEntity.class, PathMetadataFactory.forVariable(variable), null, "order_line");
    }

    public QOrderLineEntity(PathMetadata metadata) {
        super(OrderLineEntity.class, metadata, null, "order_line");
    }
}
