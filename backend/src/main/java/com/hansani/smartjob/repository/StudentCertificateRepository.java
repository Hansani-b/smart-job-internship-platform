
package com.hansani.smartjob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hansani.smartjob.entity.StudentCertificate;

public interface StudentCertificateRepository
        extends JpaRepository<StudentCertificate, Long> {

    List<StudentCertificate> findByStudentIdOrderByIdDesc(Long studentId);
}
