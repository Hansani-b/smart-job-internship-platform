package com.hansani.smartjob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hansani.smartjob.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}