package com.hansani.smartjob.dto;

public class CompanyResponseDto {

    private Long id;
    private String companyName;
    private String industry;
    private String description;
    private String website;
    private String location;
    private String phone;
    private String email;
    private String role;

    public CompanyResponseDto() {
    }

    public CompanyResponseDto(
            Long id,
            String companyName,
            String industry,
            String description,
            String website,
            String location,
            String phone,
            String email,
            String role) {

        this.id = id;
        this.companyName = companyName;
        this.industry = industry;
        this.description = description;
        this.website = website;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}