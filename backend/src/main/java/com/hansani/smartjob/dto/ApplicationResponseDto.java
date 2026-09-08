package com.hansani.smartjob.dto;

import java.time.LocalDateTime;

public class ApplicationResponseDto {

    private Long id;

    private Long jobId;
    private String jobTitle;
    private String companyName;

    private Long studentId;
    private String studentName;

    private String status;
    private LocalDateTime appliedAt;

    public ApplicationResponseDto() {
    }

    public ApplicationResponseDto(
            Long id,
            Long jobId,
            String jobTitle,
            String companyName,
            Long studentId,
            String studentName,
            String status,
            LocalDateTime appliedAt) {

        this.id = id;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }
}