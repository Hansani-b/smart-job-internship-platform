package com.hansani.smartjob.dto;

import java.time.LocalDate;

public class JobResponseDto {

    private Long id;
    private String title;
    private String description;
    private String jobType;
    private String location;
    private Double salary;
    private LocalDate deadline;
    private String status;

    private Long companyId;
    private String companyName;

    public JobResponseDto() {
    }

    public JobResponseDto(
            Long id,
            String title,
            String description,
            String jobType,
            String location,
            Double salary,
            LocalDate deadline,
            String status,
            Long companyId,
            String companyName) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.jobType = jobType;
        this.location = location;
        this.salary = salary;
        this.deadline = deadline;
        this.status = status;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getJobType() {
        return jobType;
    }

    public String getLocation() {
        return location;
    }

    public Double getSalary() {
        return salary;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
}