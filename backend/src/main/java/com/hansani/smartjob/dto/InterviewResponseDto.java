package com.hansani.smartjob.dto;

import java.time.LocalDateTime;

public class InterviewResponseDto {

    private Long id;

    private Long applicationId;
    private String jobTitle;
    private String studentName;
    private String companyName;

    private LocalDateTime interviewDate;
    private String interviewType;
    private String meetingLink;
    private String notes;

    public InterviewResponseDto() {
    }

    public InterviewResponseDto(
            Long id,
            Long applicationId,
            String jobTitle,
            String studentName,
            String companyName,
            LocalDateTime interviewDate,
            String interviewType,
            String meetingLink,
            String notes) {

        this.id = id;
        this.applicationId = applicationId;
        this.jobTitle = jobTitle;
        this.studentName = studentName;
        this.companyName = companyName;
        this.interviewDate = interviewDate;
        this.interviewType = interviewType;
        this.meetingLink = meetingLink;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDateTime getInterviewDate() {
        return interviewDate;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public String getNotes() {
        return notes;
    }
}