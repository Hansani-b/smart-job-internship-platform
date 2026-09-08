package com.hansani.smartjob.controller;

import com.hansani.smartjob.dto.StudentResponseDto;
import com.hansani.smartjob.entity.Student;
import com.hansani.smartjob.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(Authentication authentication) {

        String email = authentication.getName();

        return studentService.getStudentByEmail(email)
                .<ResponseEntity<?>>map(student ->
                        ResponseEntity.ok(toDto(student))
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Student profile not found"));
    }

    @PostMapping("/me")
    public ResponseEntity<?> createMyProfile(
            Authentication authentication,
            @RequestBody Student student) {

        String email = authentication.getName();

        if (studentService.getStudentByEmail(email).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Student profile already exists");
        }

        Student savedStudent =
                studentService.createStudent(email, student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toDto(savedStudent));
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateMyProfile(
            Authentication authentication,
            @RequestBody Student student) {

        String email = authentication.getName();

        try {
            Student updatedStudent =
                    studentService.updateStudent(email, student);

            return ResponseEntity.ok(toDto(updatedStudent));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    private StudentResponseDto toDto(Student student) {

        return new StudentResponseDto(
                student.getId(),
                student.getFullName(),
                student.getPhone(),
                student.getUniversity(),
                student.getDegree(),
                student.getGraduationYear(),
                student.getBio(),
                student.getUser().getEmail(),
                student.getUser().getRole()
        );
    }
}