package com.example.dataConsumer.querydsl.table;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.example.dataConsumer.domain.model.CustomerEntity;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.PrimaryKey;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SchemaAndTable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class QCustomerEntity extends RelationalPathBase<CustomerEntity> {

    public static final QCustomerEntity customer = new QCustomerEntity("customer");

    public final NumberPath<Long> id = createNumber("id", Long.class);
    public final StringPath email = createString("email");
    public final StringPath status = createString("status");
    public final DateTimePath<LocalDateTime> createdAt = createDateTime("created_at", LocalDateTime.class);

    public final PrimaryKey<CustomerEntity> pk = createPrimaryKey(id);

    public QCustomerEntity(String variable) {
        super(CustomerEntity.class, forVariable(variable), null, "customer");
    }

    public QCustomerEntity(Path<? extends CustomerEntity> path) {
        super(path.getType(), path.getMetadata(), null, "customer");
    }

    public QCustomerEntity(PathMetadata metadata) {
        super(CustomerEntity.class, metadata, null, "customer");
    }

    @Override
    public List<Path<?>> getPrimaryKeyFields() {
        return Arrays.asList(id);
    }

    @Override
    public SchemaAndTable getSchemaAndTable() {
        return new SchemaAndTable(null, "customer");
    }
}
