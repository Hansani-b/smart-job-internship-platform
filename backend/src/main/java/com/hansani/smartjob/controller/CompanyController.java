package com.hansani.smartjob.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansani.smartjob.dto.CompanyResponseDto;
import com.hansani.smartjob.entity.Company;
import com.hansani.smartjob.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(
            Authentication authentication) {

        String email = authentication.getName();

        return companyService.getCompanyByEmail(email)
                .<ResponseEntity<?>>map(company ->
                        ResponseEntity.ok(toDto(company))
                )
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Company profile not found"));
    }

    @PostMapping("/me")
    public ResponseEntity<?> createMyProfile(
            Authentication authentication,
            @RequestBody Company company) {

        String email = authentication.getName();

        if (companyService.getCompanyByEmail(email).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Company profile already exists");
        }

        Company savedCompany =
                companyService.createCompany(email, company);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toDto(savedCompany));
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateMyProfile(
            Authentication authentication,
            @RequestBody Company company) {

        String email = authentication.getName();

        try {
            Company updatedCompany =
                    companyService.updateCompany(email, company);

            return ResponseEntity.ok(toDto(updatedCompany));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    private CompanyResponseDto toDto(Company company) {

        return new CompanyResponseDto(
                company.getId(),
                company.getCompanyName(),
                company.getIndustry(),
                company.getDescription(),
                company.getWebsite(),
                company.getLocation(),
                company.getPhone(),
                company.getUser().getEmail(),
                company.getUser().getRole()
        );
    }
}