
package com.hansani.smartjob.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansani.smartjob.entity.StudentCertificate;
import com.hansani.smartjob.service.StudentCertificateService;

@RestController
@RequestMapping("/api/students/certificates")
public class StudentCertificateController {

    private final StudentCertificateService certificateService;

    public StudentCertificateController(
            StudentCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping
    public ResponseEntity<?> addCertificate(
            Authentication authentication,
            @RequestBody StudentCertificate certificate) {

        try {
            StudentCertificate savedCertificate =
                    certificateService.addCertificate(
                            authentication.getName(), certificate);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(toResponse(savedCertificate));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getMyCertificates(
            Authentication authentication) {

        try {
            List<StudentCertificate> certificates =
                    certificateService.getMyCertificates(
                            authentication.getName());

            return ResponseEntity.ok(
                    certificates.stream().map(this::toResponse).toList());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{certificateId}")
    public ResponseEntity<?> updateCertificate(
            Authentication authentication,
            @PathVariable Long certificateId,
            @RequestBody StudentCertificate certificate) {

        try {
            StudentCertificate updatedCertificate =
                    certificateService.updateCertificate(
                            authentication.getName(),
                            certificateId,
                            certificate);

            return ResponseEntity.ok(toResponse(updatedCertificate));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{certificateId}")
    public ResponseEntity<?> deleteCertificate(
            Authentication authentication,
            @PathVariable Long certificateId) {

        try {
            certificateService.deleteCertificate(
                    authentication.getName(), certificateId);

            return ResponseEntity.ok("Certificate deleted successfully");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private CertificateResponse toResponse(StudentCertificate certificate) {
        return new CertificateResponse(
                certificate.getId(),
                certificate.getCertificateName(),
                certificate.getIssuingOrganization(),
                certificate.getIssueDate(),
                certificate.getExpirationDate(),
                certificate.getCredentialId(),
                certificate.getCredentialUrl()
        );
    }

    public static class CertificateResponse {

        private Long id;
        private String certificateName;
        private String issuingOrganization;
        private LocalDate issueDate;
        private LocalDate expirationDate;
        private String credentialId;
        private String credentialUrl;

        public CertificateResponse(
                Long id,
                String certificateName,
                String issuingOrganization,
                LocalDate issueDate,
                LocalDate expirationDate,
                String credentialId,
                String credentialUrl) {

            this.id = id;
            this.certificateName = certificateName;
            this.issuingOrganization = issuingOrganization;
            this.issueDate = issueDate;
            this.expirationDate = expirationDate;
            this.credentialId = credentialId;
            this.credentialUrl = credentialUrl;
        }

        public Long getId() { return id; }
        public String getCertificateName() { return certificateName; }
        public String getIssuingOrganization() { return issuingOrganization; }
        public LocalDate getIssueDate() { return issueDate; }
        public LocalDate getExpirationDate() { return expirationDate; }
        public String getCredentialId() { return credentialId; }
        public String getCredentialUrl() { return credentialUrl; }
    }
}
