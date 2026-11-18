package com.hansani.smartjob.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hansani.smartjob.entity.StudentSkill;
import com.hansani.smartjob.service.SkillService;

@RestController
@RequestMapping("/api/students/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<?> addSkill(
            Authentication authentication,
            @RequestParam String skillName) {

        String email = authentication.getName();

        try {

            StudentSkill studentSkill =
                    skillService.addSkill(
                            email,
                            skillName
                    );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(toResponse(studentSkill));

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getMySkills(
            Authentication authentication) {

        String email = authentication.getName();

        try {

            List<StudentSkill> skills =
                    skillService.getMySkills(email);

            return ResponseEntity.ok(
                    skills.stream()
                            .map(this::toResponse)
                            .toList()
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<?> removeSkill(
            Authentication authentication,
            @PathVariable Long skillId) {

        String email = authentication.getName();

        try {

            skillService.removeSkill(
                    email,
                    skillId
            );

            return ResponseEntity.ok(
                    "Skill removed successfully"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    private SkillResponse toResponse(
            StudentSkill studentSkill) {

        return new SkillResponse(
                studentSkill.getId(),
                studentSkill.getSkill().getId(),
                studentSkill.getSkill().getName()
        );
    }

    public static class SkillResponse {

        private Long id;
        private Long skillId;
        private String skillName;

        public SkillResponse(
                Long id,
                Long skillId,
                String skillName) {

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