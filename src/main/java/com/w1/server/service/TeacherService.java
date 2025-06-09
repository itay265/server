package com.w1.server.service;

import com.w1.server.entity.Teacher;
import com.w1.server.entity.dto.TeacherDto;
import com.w1.server.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherDto createTeacher(TeacherDto dto) {
        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setSubject(dto.getSubject());

        Teacher saved = teacherRepository.save(teacher);

        dto.setId(saved.getId());
        return dto;
    }

    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TeacherDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        return convertToDto(teacher);
    }

    private TeacherDto convertToDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setSubject(teacher.getSubject());
        return dto;
    }
}
