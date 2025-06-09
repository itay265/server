package com.w1.server.mapper;

import com.w1.server.dto.StudentDto;
import com.w1.server.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentDto dto) {
        if (dto == null) return null;

        return new Student(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAge(),
                dto.getEmail()
        );
    }

    public StudentDto toDto(Student entity) {
        if (entity == null) return null;

        return new StudentDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getAge(),
                entity.getEmail()
        );
    }

    public void updateEntityFromDto(Student entity, StudentDto dto) {
        if (entity != null && dto != null) {
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setAge(dto.getAge());
            entity.setEmail(dto.getEmail());
        }
    }
}
