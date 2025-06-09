package com.w1.server.service;

import com.w1.server.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto addStudent(StudentDto studentDto);

    StudentDto updateStudent(StudentDto studentDto, Long id);

    void deleteStudent(Long id);
}
