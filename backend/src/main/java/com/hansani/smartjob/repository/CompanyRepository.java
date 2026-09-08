package com.hansani.smartjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hansani.smartjob.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByUserId(Long userId);

    Optional<Company> findByUserEmail(String email);
}