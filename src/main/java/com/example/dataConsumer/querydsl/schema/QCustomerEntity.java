package com.example.dataConsumer.querydsl.schema;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.PrimaryKey;
import com.querydsl.core.types.PathMetadataFactory;
import java.time.LocalDateTime;

public class QCustomerEntity extends RelationalPathBase<CustomerEntity> {

    public static final QCustomerEntity customer = new QCustomerEntity("customer");

    public final NumberPath<Long> id = createNumber("id", Long.class);
    public final StringPath email = createString("email");
    public final StringPath status = createString("status");
    public final DateTimePath<LocalDateTime> createdAt = createDateTime("created_at", LocalDateTime.class);

    public final PrimaryKey<CustomerEntity> customerPkey = createPrimaryKey(id);

    public QCustomerEntity(String variable) {
        super(CustomerEntity.class, PathMetadataFactory.forVariable(variable), null, "customer");
    }

    public QCustomerEntity(PathMetadata metadata) {
        super(CustomerEntity.class, metadata, null, "customer");
    }
}
