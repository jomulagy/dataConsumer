-- Insert 1,000,000 customers using PostgreSQL generate_series to avoid client-side loops
INSERT INTO customer (id, email, status, created_at)
SELECT gs AS id,
       concat('user', gs, '@example.com') AS email,
       CASE WHEN gs % 2 = 0 THEN 'ACTIVE' ELSE 'INACTIVE' END AS status,
       NOW() - (gs || ' seconds')::interval AS created_at
FROM generate_series(1, 1000000) AS gs;

-- Insert 1,000,000 order lines across 200,000 orders (5 lines each)
INSERT INTO order_line (order_id, line_no, sku, quantity, amount)
SELECT (gs - 1) / 5 + 1 AS order_id,
       ((gs - 1) % 5) + 1 AS line_no,
       concat('SKU-', ((gs - 1) % 1000) + 1) AS sku,
       ((gs - 1) % 7) + 1 AS quantity,
       (((gs - 1) % 50) + 1) * 10.00 AS amount
FROM generate_series(1, 1000000) AS gs;
