
package com.hansani.smartjob.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Student;
import com.hansani.smartjob.entity.StudentCertificate;
import com.hansani.smartjob.repository.StudentCertificateRepository;
import com.hansani.smartjob.repository.StudentRepository;

@Service
public class StudentCertificateService {

    private final StudentCertificateRepository certificateRepository;
    private final StudentRepository studentRepository;

    public StudentCertificateService(
            StudentCertificateRepository certificateRepository,
            StudentRepository studentRepository) {

        this.certificateRepository = certificateRepository;
        this.studentRepository = studentRepository;
    }

    public StudentCertificate addCertificate(
            String email, StudentCertificate certificate) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        certificate.setStudent(student);

        return certificateRepository.save(certificate);
    }

    public List<StudentCertificate> getMyCertificates(String email) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return certificateRepository
                .findByStudentIdOrderByIdDesc(student.getId());
    }

    public StudentCertificate updateCertificate(
            String email, Long certificateId,
            StudentCertificate updatedCertificate) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        StudentCertificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        if (!certificate.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException(
                    "You are not authorized to update this certificate");
        }

        certificate.setCertificateName(updatedCertificate.getCertificateName());
        certificate.setIssuingOrganization(
                updatedCertificate.getIssuingOrganization());
        certificate.setIssueDate(updatedCertificate.getIssueDate());
        certificate.setExpirationDate(updatedCertificate.getExpirationDate());
        certificate.setCredentialId(updatedCertificate.getCredentialId());
        certificate.setCredentialUrl(updatedCertificate.getCredentialUrl());

        return certificateRepository.save(certificate);
    }

    public void deleteCertificate(String email, Long certificateId) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        StudentCertificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        if (!certificate.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException(
                    "You are not authorized to delete this certificate");
        }

        certificateRepository.delete(certificate);
    }
}
