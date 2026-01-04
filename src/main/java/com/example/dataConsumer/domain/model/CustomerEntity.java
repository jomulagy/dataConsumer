package com.example.dataConsumer.domain.model;

import java.time.LocalDateTime;

public class CustomerEntity {

    private Long id;
    private String email;
    private String status;
    private LocalDateTime createdAt;

    public CustomerEntity() {
    }

    public CustomerEntity(Long id, String email, String status, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
