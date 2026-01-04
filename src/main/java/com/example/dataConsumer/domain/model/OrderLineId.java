package com.example.dataConsumer.domain.model;

import java.io.Serializable;
import java.util.Objects;

public class OrderLineId implements Serializable {

    private Long orderId;
    private Integer lineNo;

    public OrderLineId() {
    }

    public OrderLineId(Long orderId, Integer lineNo) {
        this.orderId = orderId;
        this.lineNo = lineNo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderLineId)) {
            return false;
        }
        OrderLineId that = (OrderLineId) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(lineNo, that.lineNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, lineNo);
    }
}
