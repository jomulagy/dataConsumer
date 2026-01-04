package com.example.dataConsumer.domain.model;

import java.math.BigDecimal;

public record OrderLineRecord(Long orderId, Integer lineNo, String sku, Integer quantity, BigDecimal amount) {
}
