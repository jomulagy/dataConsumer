package com.example.dataConsumer.domain.model;

import java.math.BigDecimal;

public class OrderLineEntity {

    private Long orderId;
    private Integer lineNo;
    private String sku;
    private Integer quantity;
    private BigDecimal amount;

    public OrderLineEntity() {
    }

    public OrderLineEntity(Long orderId, Integer lineNo, String sku, Integer quantity, BigDecimal amount) {
        this.orderId = orderId;
        this.lineNo = lineNo;
        this.sku = sku;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
