package com.example.dataConsumer.domain.model;

import java.time.LocalDateTime;

public record CustomerRecord(Long id, String email, String status, LocalDateTime createdAt) {
}
