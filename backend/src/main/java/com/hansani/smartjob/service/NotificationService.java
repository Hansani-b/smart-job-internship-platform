package com.hansani.smartjob.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Notification;
import com.hansani.smartjob.entity.User;
import com.hansani.smartjob.repository.NotificationRepository;
import com.hansani.smartjob.repository.UserRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(
            NotificationRepository notificationRepository,
            UserRepository userRepository) {

        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public Notification createNotification(
            Long userId,
            String message) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Notification notification = new Notification();

        notification.setUser(user);
        notification.setMessage(message);
        notification.setRead(false);

        return notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(user.getId());
    }

    public void markAsRead(Long notificationId) {

        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found"));

        notification.setRead(true);

        notificationRepository.save(notification);
    }
}