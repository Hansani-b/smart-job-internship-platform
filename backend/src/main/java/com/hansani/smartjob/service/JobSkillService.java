
package com.hansani.smartjob.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Company;
import com.hansani.smartjob.entity.Job;
import com.hansani.smartjob.entity.JobSkill;
import com.hansani.smartjob.entity.Skill;
import com.hansani.smartjob.repository.CompanyRepository;
import com.hansani.smartjob.repository.JobRepository;
import com.hansani.smartjob.repository.JobSkillRepository;
import com.hansani.smartjob.repository.SkillRepository;

@Service
public class JobSkillService {

    private final JobSkillRepository jobSkillRepository;
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final CompanyRepository companyRepository;

    public JobSkillService(
            JobSkillRepository jobSkillRepository,
            JobRepository jobRepository,
            SkillRepository skillRepository,
            CompanyRepository companyRepository) {

        this.jobSkillRepository = jobSkillRepository;
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
        this.companyRepository = companyRepository;
    }

    public JobSkill addRequiredSkill(String email, Long jobId, String skillName) {

        Company company = companyRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Company profile not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (!job.getCompany().getId().equals(company.getId())) {
            throw new RuntimeException("You are not authorized to manage this job");
        }

        Skill skill = skillRepository.findByNameIgnoreCase(skillName.trim())
                .orElseGet(() -> {
                    Skill newSkill = new Skill();
                    newSkill.setName(skillName.trim());
                    return skillRepository.save(newSkill);
                });

        if (jobSkillRepository.existsByJobIdAndSkillId(jobId, skill.getId())) {
            throw new RuntimeException("Skill already added to this job");
        }

        JobSkill jobSkill = new JobSkill(job, skill);

        return jobSkillRepository.save(jobSkill);
    }

    public List<JobSkill> getRequiredSkills(Long jobId) {

        if (!jobRepository.existsById(jobId)) {
            throw new RuntimeException("Job not found");
        }

        return jobSkillRepository.findByJobId(jobId);
    }
}

