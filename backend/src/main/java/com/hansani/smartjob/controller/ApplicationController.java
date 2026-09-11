package com.hansani.smartjob.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hansani.smartjob.dto.ApplicationResponseDto;
import com.hansani.smartjob.entity.Application;
import com.hansani.smartjob.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Student - Apply for a job
    @PostMapping("/job/{jobId}")
    public ResponseEntity<?> applyForJob(
            Authentication authentication,
            @PathVariable Long jobId) {

        String email = authentication.getName();

        try {
            Application application =
                    applicationService.applyForJob(email, jobId);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(toDto(application));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // Student - View my applications
    @GetMapping("/my")
    public ResponseEntity<?> getMyApplications(
            Authentication authentication) {

        String email = authentication.getName();

        try {
            List<ApplicationResponseDto> applications =
                    applicationService
                            .getMyApplications(email)
                            .stream()
                            .map(this::toDto)
                            .toList();

            return ResponseEntity.ok(applications);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // Company - View applicants for a job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<?> getJobApplications(
            Authentication authentication,
            @PathVariable Long jobId) {

        String email = authentication.getName();

        try {
            List<ApplicationResponseDto> applications =
                    applicationService
                            .getJobApplications(email, jobId)
                            .stream()
                            .map(this::toDto)
                            .toList();

            return ResponseEntity.ok(applications);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    private ApplicationResponseDto toDto(Application application) {

        return new ApplicationResponseDto(
                application.getId(),
                application.getJob().getId(),
                application.getJob().getTitle(),
                application.getJob().getCompany().getCompanyName(),
                application.getStudent().getId(),
                application.getStudent().getFullName(),
                application.getStatus(),
                application.getAppliedAt()
        );
    }

    @PutMapping("/{applicationId}/status")
public ResponseEntity<?> updateApplicationStatus(
        Authentication authentication,
        @PathVariable Long applicationId,
        @RequestParam String status) {

    String email = authentication.getName();

    try {
        Application application =
                applicationService.updateApplicationStatus(
                        email,
                        applicationId,
                        status
                );

        return ResponseEntity.ok(toDto(application));

    } catch (RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }
}
}