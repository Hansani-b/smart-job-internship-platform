package com.hansani.smartjob.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hansani.smartjob.entity.Skill;
import com.hansani.smartjob.entity.Student;
import com.hansani.smartjob.entity.StudentSkill;
import com.hansani.smartjob.repository.SkillRepository;
import com.hansani.smartjob.repository.StudentRepository;
import com.hansani.smartjob.repository.StudentSkillRepository;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final StudentRepository studentRepository;
    private final StudentSkillRepository studentSkillRepository;

    public SkillService(
            SkillRepository skillRepository,
            StudentRepository studentRepository,
            StudentSkillRepository studentSkillRepository) {

        this.skillRepository = skillRepository;
        this.studentRepository = studentRepository;
        this.studentSkillRepository = studentSkillRepository;
    }

    public StudentSkill addSkill(
            String email,
            String skillName) {

        Student student = studentRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found"));

        Skill skill = skillRepository
                .findByNameIgnoreCase(skillName)
                .orElseGet(() -> {

                    Skill newSkill = new Skill();
                    newSkill.setName(skillName);

                    return skillRepository.save(newSkill);
                });

        if (studentSkillRepository
                .existsByStudentIdAndSkillId(
                        student.getId(),
                        skill.getId())) {

            throw new RuntimeException(
                    "Skill already added");
        }

        StudentSkill studentSkill = new StudentSkill();

        studentSkill.setStudent(student);
        studentSkill.setSkill(skill);

        return studentSkillRepository.save(studentSkill);
    }

    public List<StudentSkill> getMySkills(
            String email) {

        Student student = studentRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found"));

        return studentSkillRepository
                .findByStudentId(student.getId());
    }

    public void removeSkill(
            String email,
            Long skillId) {

        Student student = studentRepository
                .findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student profile not found"));

        if (!studentSkillRepository
                .existsByStudentIdAndSkillId(
                        student.getId(),
                        skillId)) {

            throw new RuntimeException(
                    "Skill not found");
        }

        studentSkillRepository
                .deleteByStudentIdAndSkillId(
                        student.getId(),
                        skillId);
    }
}