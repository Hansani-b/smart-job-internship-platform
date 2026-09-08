package com.hansani.smartjob.dto;

public class StudentResponseDto {

    private Long id;
    private String fullName;
    private String phone;
    private String university;
    private String degree;
    private String graduationYear;
    private String bio;
    private String email;
    private String role;

    public StudentResponseDto() {
    }

    public StudentResponseDto(
            Long id,
            String fullName,
            String phone,
            String university,
            String degree,
            String graduationYear,
            String bio,
            String email,
            String role) {

        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.university = university;
        this.degree = degree;
        this.graduationYear = graduationYear;
        this.bio = bio;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getUniversity() {
        return university;
    }

    public String getDegree() {
        return degree;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}