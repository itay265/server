package com.w1.server.service;

import com.w1.server.entity.Student;
import com.w1.server.entity.StudentDetails;
import com.w1.server.entity.Teacher;
import com.w1.server.entity.dto.StudentDetailsDto;
import com.w1.server.entity.dto.StudentDto;
import com.w1.server.repository.StudentRepository;
import com.w1.server.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public StudentDto createStudent(StudentDto dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        // סטודנט דיטיילס
        if (dto.getDetails() != null) {
            StudentDetails details = new StudentDetails();
            details.setAddress(dto.getDetails().getAddress());
            details.setEmergencyContactName(dto.getDetails().getEmergencyContactName());
            details.setEmergencyContactPhone(dto.getDetails().getEmergencyContactPhone());
            details.setPhoneNumber(dto.getDetails().getPhoneNumber());
            details.setStudent(student);
            student.setDetails(details);
        }

        // מורים
        if (dto.getTeacherIds() != null && !dto.getTeacherIds().isEmpty()) {
            Set<Teacher> teachers = new HashSet<>();
            for (Long teacherId : dto.getTeacherIds()) {
                Teacher teacher = teacherRepository.findById(teacherId)
                        .orElseThrow(() -> new RuntimeException("Teacher not found: " + teacherId));
                teachers.add(teacher);
            }
            student.setTeachers(teachers);
            teachers.forEach(t -> t.getStudents().add(student));
        }

        Student saved = studentRepository.save(student);
        dto.setId(saved.getId());
        return dto;
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return convertToDto(student);
    }

    private StudentDto convertToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setAge(student.getAge());
        dto.setEmail(student.getEmail());

        if (student.getDetails() != null) {
            StudentDetailsDto detailsDto = new StudentDetailsDto();
            detailsDto.setAddress(student.getDetails().getAddress());
            detailsDto.setEmergencyContactName(student.getDetails().getEmergencyContactName());
            detailsDto.setEmergencyContactPhone(student.getDetails().getEmergencyContactPhone());
            detailsDto.setPhoneNumber(student.getDetails().getPhoneNumber());
            dto.setDetails(detailsDto);
        }

        Set<Long> teacherIds = student.getTeachers().stream().map(Teacher::getId).collect(Collectors.toSet());
        dto.setTeacherIds(teacherIds);

        return dto;
    }
}
