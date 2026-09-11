package com.hansani.smartjob.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansani.smartjob.dto.NotificationResponseDto;
import com.hansani.smartjob.entity.Notification;
import com.hansani.smartjob.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyNotifications(
            Authentication authentication) {

        String email = authentication.getName();

        try {

            List<NotificationResponseDto> notifications =
                    notificationService
                            .getUserNotifications(email)
                            .stream()
                            .map(this::toDto)
                            .toList();

            return ResponseEntity.ok(notifications);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(
            @PathVariable Long notificationId) {

        try {

            notificationService
                    .markAsRead(notificationId);

            return ResponseEntity.ok(
                    "Notification marked as read"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    private NotificationResponseDto toDto(
            Notification notification) {

        return new NotificationResponseDto(
                notification.getId(),
                notification.getMessage(),
                notification.isRead(),
                notification.getCreatedAt()
        );
    }
}