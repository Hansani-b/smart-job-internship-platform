
package com.hansani.smartjob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hansani.smartjob.entity.StudentProject;

public interface StudentProjectRepository extends JpaRepository<StudentProject, Long> {

    List<StudentProject> findByStudentIdOrderByIdDesc(Long studentId);
}
