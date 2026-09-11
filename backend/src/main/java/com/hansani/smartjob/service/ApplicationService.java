package com.hansani.smartjob.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Application;
import com.hansani.smartjob.entity.Job;
import com.hansani.smartjob.entity.Student;
import com.hansani.smartjob.repository.ApplicationRepository;
import com.hansani.smartjob.repository.JobRepository;
import com.hansani.smartjob.repository.StudentRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobRepository jobRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            StudentRepository studentRepository,
            JobRepository jobRepository) {

        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobRepository = jobRepository;
    }

    public Application applyForJob(String email, Long jobId) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (applicationRepository
                .existsByStudentIdAndJobId(student.getId(), job.getId())) {

            throw new RuntimeException(
                    "You have already applied for this job");
        }

        Application application = new Application();

        application.setStudent(student);
        application.setJob(job);
        application.setStatus("PENDING");

        return applicationRepository.save(application);
    }

    public List<Application> getMyApplications(String email) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        return applicationRepository
                .findByStudentId(student.getId());
    }

    public List<Application> getJobApplications(
            String email, Long jobId) {

        return applicationRepository.findByJobId(jobId);
    }

    public Application updateApplicationStatus(
        String email,
        Long applicationId,
        String status) {

    Application application = applicationRepository
            .findById(applicationId)
            .orElseThrow(() ->
                    new RuntimeException("Application not found"));

    Job job = application.getJob();

    if (!job.getCompany().getUser().getEmail().equals(email)) {
        throw new RuntimeException(
                "You are not allowed to update this application");
    }

    if (!status.equals("PENDING")
        && !status.equals("SHORTLISTED")
        && !status.equals("INTERVIEW_SCHEDULED")
        && !status.equals("SELECTED")
        && !status.equals("REJECTED")) {

    throw new RuntimeException("Invalid application status");
}

application.setStatus(status);

return applicationRepository.save(application);
}
}