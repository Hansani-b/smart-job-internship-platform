package com.hansani.smartjob.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Company;
import com.hansani.smartjob.entity.Job;
import com.hansani.smartjob.repository.CompanyRepository;
import com.hansani.smartjob.repository.JobRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(JobRepository jobRepository,
                      CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public Job createJob(String email, Job job) {

        Company company = companyRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Company profile not found"));

        job.setCompany(company);

        if (job.getStatus() == null) {
            job.setStatus("PENDING_APPROVAL");
        }

        return jobRepository.save(job);
    }

    public List<Job> getMyJobs(String email) {

        Company company = companyRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Company profile not found"));

        return jobRepository.findByCompanyId(company.getId());
    }

    public List<Job> getPublishedJobs() {
        return jobRepository.findByStatus("PUBLISHED");
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public Job updateJob(String email, Long jobId, Job updatedJob) {

        Company company = companyRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Company profile not found"));

        Job existingJob = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (!existingJob.getCompany().getId().equals(company.getId())) {
            throw new RuntimeException(
                    "You are not allowed to update this job"
            );
        }

        existingJob.setTitle(updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setJobType(updatedJob.getJobType());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setDeadline(updatedJob.getDeadline());

        return jobRepository.save(existingJob);
    }

    public void deleteJob(String email, Long jobId) {

        Company company = companyRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Company profile not found"));

        Job existingJob = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (!existingJob.getCompany().getId().equals(company.getId())) {
            throw new RuntimeException(
                    "You are not allowed to delete this job"
            );
        }

        jobRepository.delete(existingJob);
    }
}