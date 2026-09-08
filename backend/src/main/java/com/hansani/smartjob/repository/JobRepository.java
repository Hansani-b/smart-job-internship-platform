package com.hansani.smartjob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hansani.smartjob.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByCompanyId(Long companyId);

    List<Job> findByStatus(String status);
}