package com.hansani.smartjob.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Student;
import com.hansani.smartjob.entity.User;
import com.hansani.smartjob.repository.StudentRepository;
import com.hansani.smartjob.repository.UserRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentService(StudentRepository studentRepository,
                          UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByUserEmail(email);
    }

    public Student createStudent(String email, Student student) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        student.setUser(user);

        return studentRepository.save(student);
    }

    public Student updateStudent(String email, Student updatedStudent) {

        Student existingStudent = studentRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        existingStudent.setFullName(updatedStudent.getFullName());
        existingStudent.setPhone(updatedStudent.getPhone());
        existingStudent.setUniversity(updatedStudent.getUniversity());
        existingStudent.setDegree(updatedStudent.getDegree());
        existingStudent.setGraduationYear(updatedStudent.getGraduationYear());
        existingStudent.setBio(updatedStudent.getBio());

        return studentRepository.save(existingStudent);
    }
}
