package com.hansani.smartjob.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Company;
import com.hansani.smartjob.entity.User;
import com.hansani.smartjob.repository.CompanyRepository;
import com.hansani.smartjob.repository.UserRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository,
                          UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public Optional<Company> getCompanyByEmail(String email) {
        return companyRepository.findByUserEmail(email);
    }

    public Company createCompany(String email, Company company) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        company.setUser(user);

        return companyRepository.save(company);
    }

    public Company updateCompany(String email, Company updatedCompany) {

        Company existingCompany = companyRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Company profile not found"));

        existingCompany.setCompanyName(updatedCompany.getCompanyName());
        existingCompany.setIndustry(updatedCompany.getIndustry());
        existingCompany.setDescription(updatedCompany.getDescription());
        existingCompany.setWebsite(updatedCompany.getWebsite());
        existingCompany.setLocation(updatedCompany.getLocation());
        existingCompany.setPhone(updatedCompany.getPhone());

        return companyRepository.save(existingCompany);
    }
}