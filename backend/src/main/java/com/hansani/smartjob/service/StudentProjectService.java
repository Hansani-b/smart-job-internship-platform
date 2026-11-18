
package com.hansani.smartjob.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Student;
import com.hansani.smartjob.entity.StudentProject;
import com.hansani.smartjob.repository.StudentProjectRepository;
import com.hansani.smartjob.repository.StudentRepository;

@Service
public class StudentProjectService {

    private final StudentProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public StudentProjectService(
            StudentProjectRepository projectRepository,
            StudentRepository studentRepository) {

        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
    }

    public StudentProject addProject(String email, StudentProject project) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        project.setStudent(student);

        return projectRepository.save(project);
    }

    public List<StudentProject> getMyProjects(String email) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return projectRepository.findByStudentIdOrderByIdDesc(student.getId());
    }

    public StudentProject updateProject(
            String email, Long projectId, StudentProject updatedProject) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        StudentProject project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException("You are not authorized to update this project");
        }

        project.setTitle(updatedProject.getTitle());
        project.setDescription(updatedProject.getDescription());
        project.setTechnologies(updatedProject.getTechnologies());
        project.setProjectUrl(updatedProject.getProjectUrl());
        project.setStartDate(updatedProject.getStartDate());
        project.setEndDate(updatedProject.getEndDate());
        project.setCurrentlyWorking(updatedProject.isCurrentlyWorking());

        return projectRepository.save(project);
    }

    public void deleteProject(String email, Long projectId) {

        Student student = studentRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        StudentProject project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException("You are not authorized to delete this project");
        }

        projectRepository.delete(project);
    }
}
