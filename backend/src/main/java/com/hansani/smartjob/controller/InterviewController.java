package com.hansani.smartjob.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansani.smartjob.dto.InterviewResponseDto;
import com.hansani.smartjob.entity.Interview;
import com.hansani.smartjob.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/application/{applicationId}")
    public ResponseEntity<?> scheduleInterview(
            Authentication authentication,
            @PathVariable Long applicationId,
            @RequestBody InterviewRequest request) {

        String email = authentication.getName();

        try {

            Interview interview =
                    interviewService.scheduleInterview(
                            email,
                            applicationId,
                            request.getInterviewDate(),
                            request.getInterviewType(),
                            request.getMeetingLink(),
                            request.getNotes()
                    );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(toDto(interview));

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<?> getInterview(
            Authentication authentication,
            @PathVariable Long applicationId) {

        return interviewService
                .getInterviewByApplicationId(applicationId)
                .<ResponseEntity<?>>map(interview ->
                        ResponseEntity.ok(toDto(interview))
                )
                .orElseGet(() ->
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body("Interview not found"));
    }

    private InterviewResponseDto toDto(Interview interview) {

        return new InterviewResponseDto(
                interview.getId(),
                interview.getApplication().getId(),
                interview.getApplication().getJob().getTitle(),
                interview.getApplication().getStudent().getFullName(),
                interview.getApplication().getJob()
                        .getCompany().getCompanyName(),
                interview.getInterviewDate(),
                interview.getInterviewType(),
                interview.getMeetingLink(),
                interview.getNotes()
        );
    }

    public static class InterviewRequest {

        private LocalDateTime interviewDate;
        private String interviewType;
        private String meetingLink;
        private String notes;

        public LocalDateTime getInterviewDate() {
            return interviewDate;
        }

        public void setInterviewDate(LocalDateTime interviewDate) {
            this.interviewDate = interviewDate;
        }

        public String getInterviewType() {
            return interviewType;
        }

        public void setInterviewType(String interviewType) {
            this.interviewType = interviewType;
        }

        public String getMeetingLink() {
            return meetingLink;
        }

        public void setMeetingLink(String meetingLink) {
            this.meetingLink = meetingLink;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }
}