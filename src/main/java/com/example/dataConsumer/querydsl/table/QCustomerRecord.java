package com.example.dataConsumer.querydsl.table;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.example.dataConsumer.domain.model.CustomerRecord;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SchemaAndTable;
import com.querydsl.sql.PrimaryKey;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class QCustomerRecord extends RelationalPathBase<CustomerRecord> {

    public static final QCustomerRecord customerRecord = new QCustomerRecord("customer");

    public final NumberPath<Long> id = createNumber("id", Long.class);
    public final StringPath email = createString("email");
    public final StringPath status = createString("status");
    public final DateTimePath<LocalDateTime> createdAt = createDateTime("created_at", LocalDateTime.class);

    public final PrimaryKey<CustomerRecord> pk = createPrimaryKey(id);

    public QCustomerRecord(String variable) {
        super(CustomerRecord.class, forVariable(variable), null, "customer");
    }

    public QCustomerRecord(Path<? extends CustomerRecord> path) {
        super(path.getType(), path.getMetadata(), null, "customer");
    }

    public QCustomerRecord(PathMetadata metadata) {
        super(CustomerRecord.class, metadata, null, "customer");
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
