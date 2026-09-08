package com.hansani.smartjob.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansani.smartjob.dto.JobResponseDto;
import com.hansani.smartjob.entity.Job;
import com.hansani.smartjob.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<?> createJob(
            Authentication authentication,
            @RequestBody Job job) {

        String email = authentication.getName();

        try {
            Job savedJob = jobService.createJob(email, job);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(toDto(savedJob));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyJobs(
            Authentication authentication) {

        String email = authentication.getName();

        try {
            List<JobResponseDto> jobs = jobService
                    .getMyJobs(email)
                    .stream()
                    .map(this::toDto)
                    .toList();

            return ResponseEntity.ok(jobs);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/published")
    public ResponseEntity<List<JobResponseDto>> getPublishedJobs() {

        List<JobResponseDto> jobs = jobService
                .getPublishedJobs()
                .stream()
                .map(this::toDto)
                .toList();

        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(
            @PathVariable Long id) {

        return jobService.getJobById(id)
                .<ResponseEntity<?>>map(job ->
                        ResponseEntity.ok(toDto(job))
                )
                .orElseGet(() ->
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body("Job not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody Job job) {

        String email = authentication.getName();

        try {
            Job updatedJob =
                    jobService.updateJob(email, id, job);

            return ResponseEntity.ok(toDto(updatedJob));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(
            Authentication authentication,
            @PathVariable Long id) {

        String email = authentication.getName();

        try {
            jobService.deleteJob(email, id);

            return ResponseEntity.ok("Job deleted successfully");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    private JobResponseDto toDto(Job job) {

        return new JobResponseDto(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getJobType(),
                job.getLocation(),
                job.getSalary(),
                job.getDeadline(),
                job.getStatus(),
                job.getCompany().getId(),
                job.getCompany().getCompanyName()
        );
    }
}