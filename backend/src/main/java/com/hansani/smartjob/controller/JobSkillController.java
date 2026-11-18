
package com.hansani.smartjob.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hansani.smartjob.entity.JobSkill;
import com.hansani.smartjob.service.JobSkillService;

@RestController
@RequestMapping("/api/jobs/{jobId}/skills")
public class JobSkillController {

    private final JobSkillService jobSkillService;

    public JobSkillController(JobSkillService jobSkillService) {
        this.jobSkillService = jobSkillService;
    }

    @PostMapping
    public ResponseEntity<?> addRequiredSkill(
            Authentication authentication,
            @PathVariable Long jobId,
            @RequestParam String skillName) {

        try {
            JobSkill jobSkill = jobSkillService.addRequiredSkill(
                    authentication.getName(), jobId, skillName);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(toResponse(jobSkill));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getRequiredSkills(@PathVariable Long jobId) {

        try {
            List<JobSkill> skills =
                    jobSkillService.getRequiredSkills(jobId);

            return ResponseEntity.ok(
                    skills.stream().map(this::toResponse).toList());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private JobSkillResponse toResponse(JobSkill jobSkill) {
        return new JobSkillResponse(
                jobSkill.getId(),
                jobSkill.getSkill().getId(),
                jobSkill.getSkill().getName()
        );
    }

    public static class JobSkillResponse {

        private Long id;
        private Long skillId;
        private String skillName;

        public JobSkillResponse(Long id, Long skillId, String skillName) {
            this.id = id;
            this.skillId = skillId;
            this.skillName = skillName;
        }

        public Long getId() {
            return id;
        }

        public Long getSkillId() {
            return skillId;
        }

        public String getSkillName() {
            return skillName;
        }
    }
}

