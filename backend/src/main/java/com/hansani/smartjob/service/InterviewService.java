package com.hansani.smartjob.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Application;
import com.hansani.smartjob.entity.Interview;
import com.hansani.smartjob.repository.ApplicationRepository;
import com.hansani.smartjob.repository.InterviewRepository;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;
    private final NotificationService notificationService;

    public InterviewService(
            InterviewRepository interviewRepository,
            ApplicationRepository applicationRepository,
            NotificationService notificationService) {

        this.interviewRepository = interviewRepository;
        this.applicationRepository = applicationRepository;
        this.notificationService = notificationService;
    }

    public Interview scheduleInterview(
            String email,
            Long applicationId,
            LocalDateTime interviewDate,
            String interviewType,
            String meetingLink,
            String notes) {

        Application application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));

        if (!application.getJob()
                .getCompany()
                .getUser()
                .getEmail()
                .equals(email)) {

            throw new RuntimeException(
                    "You are not allowed to schedule this interview");
        }

        if (interviewRepository
                .findByApplicationId(applicationId)
                .isPresent()) {

            throw new RuntimeException(
                    "Interview already scheduled for this application");
        }

        Interview interview = new Interview(
                application,
                interviewDate,
                interviewType,
                meetingLink,
                notes
        );

        application.setStatus("INTERVIEW_SCHEDULED");

        applicationRepository.save(application);

        Interview savedInterview =
                interviewRepository.save(interview);

        // Create notification for the student
        Long studentUserId =
                application.getStudent()
                        .getUser()
                        .getId();

        String message =
                "Interview scheduled for "
                        + application.getJob().getTitle()
                        + " on "
                        + interviewDate;

        notificationService.createNotification(
                studentUserId,
                message
        );

        return savedInterview;
    }

    public Optional<Interview> getInterviewByApplicationId(
            Long applicationId) {

        return interviewRepository
                .findByApplicationId(applicationId);
    }
}