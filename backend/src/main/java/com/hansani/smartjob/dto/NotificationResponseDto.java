package com.hansani.smartjob.dto;

import java.time.LocalDateTime;

public class NotificationResponseDto {

    private Long id;
    private String message;
    private boolean read;
    private LocalDateTime createdAt;

    public NotificationResponseDto() {
    }

    public NotificationResponseDto(
            Long id,
            String message,
            boolean read,
            LocalDateTime createdAt) {

        this.id = id;
        this.message = message;
        this.read = read;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}