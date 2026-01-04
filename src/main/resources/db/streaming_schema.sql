CREATE TABLE IF NOT EXISTS customer (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    status VARCHAR(40) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS order_line (
    order_id BIGINT NOT NULL,
    line_no INTEGER NOT NULL,
    sku VARCHAR(120) NOT NULL,
    quantity INTEGER NOT NULL,
    amount NUMERIC(18,2) NOT NULL,
    PRIMARY KEY (order_id, line_no)
);

CREATE INDEX IF NOT EXISTS idx_order_line_order ON order_line(order_id);
