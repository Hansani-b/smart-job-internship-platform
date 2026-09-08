package com.hansani.smartjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hansani.smartjob.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUserId(Long userId);

    Optional<Student> findByUserEmail(String email);
}
