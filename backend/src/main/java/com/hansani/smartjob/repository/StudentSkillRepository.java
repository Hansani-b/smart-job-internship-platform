package com.hansani.smartjob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hansani.smartjob.entity.StudentSkill;

public interface StudentSkillRepository
        extends JpaRepository<StudentSkill, Long> {

    List<StudentSkill> findByStudentId(Long studentId);

    boolean existsByStudentIdAndSkillId(
            Long studentId,
            Long skillId
    );

    void deleteByStudentIdAndSkillId(
            Long studentId,
            Long skillId
    );
}