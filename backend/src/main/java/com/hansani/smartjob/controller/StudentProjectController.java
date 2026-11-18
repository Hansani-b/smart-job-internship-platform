
package com.hansani.smartjob.controller;

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

import com.hansani.smartjob.entity.StudentProject;
import com.hansani.smartjob.service.StudentProjectService;

@RestController
@RequestMapping("/api/students/projects")
public class StudentProjectController {

    private final StudentProjectService projectService;

    public StudentProjectController(StudentProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<?> addProject(
            Authentication authentication,
            @RequestBody StudentProject project) {

        try {
            StudentProject savedProject =
                    projectService.addProject(authentication.getName(), project);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(toResponse(savedProject));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getMyProjects(Authentication authentication) {

        try {
            List<StudentProject> projects =
                    projectService.getMyProjects(authentication.getName());

            return ResponseEntity.ok(
                    projects.stream().map(this::toResponse).toList());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(
            Authentication authentication,
            @PathVariable Long projectId,
            @RequestBody StudentProject project) {

        try {
            StudentProject updatedProject = projectService.updateProject(
                    authentication.getName(), projectId, project);

            return ResponseEntity.ok(toResponse(updatedProject));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(
            Authentication authentication,
            @PathVariable Long projectId) {

        try {
            projectService.deleteProject(
                    authentication.getName(), projectId);

            return ResponseEntity.ok("Project deleted successfully");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private ProjectResponse toResponse(StudentProject project) {

        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getTechnologies(),
                project.getProjectUrl(),
                project.getStartDate(),
                project.getEndDate(),
                project.isCurrentlyWorking()
        );
    }

    public static class ProjectResponse {

        private Long id;
        private String title;
        private String description;
        private String technologies;
        private String projectUrl;
        private java.time.LocalDate startDate;
        private java.time.LocalDate endDate;
        private boolean currentlyWorking;

        public ProjectResponse(Long id, String title, String description,
                String technologies, String projectUrl,
                java.time.LocalDate startDate,
                java.time.LocalDate endDate, boolean currentlyWorking) {

            this.id = id;
            this.title = title;
            this.description = description;
            this.technologies = technologies;
            this.projectUrl = projectUrl;
            this.startDate = startDate;
            this.endDate = endDate;
            this.currentlyWorking = currentlyWorking;
        }

        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getTechnologies() { return technologies; }
        public String getProjectUrl() { return projectUrl; }
        public java.time.LocalDate getStartDate() { return startDate; }
        public java.time.LocalDate getEndDate() { return endDate; }
        public boolean isCurrentlyWorking() { return currentlyWorking; }
    }
}
