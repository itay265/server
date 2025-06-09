package com.w1.server.controller;

import com.w1.server.dto.StudentDto;
import com.w1.server.response.StandardResponse;
import com.w1.server.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(new StandardResponse("success", students, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getStudent(@PathVariable Long id) {
        StudentDto student = studentService.getStudentById(id);
        return ResponseEntity.ok(new StandardResponse("success", student, null));
    }

    @PostMapping
    public ResponseEntity<StandardResponse> addStudent(@Valid @RequestBody StudentDto studentDto) {
        StudentDto added = studentService.addStudent(studentDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(added.getId())
                .toUri();

        return ResponseEntity.created(location).body(new StandardResponse("success", added, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable Long id) {
        StudentDto updated = studentService.updateStudent(studentDto, id);
        return ResponseEntity.ok(new StandardResponse("success", updated, null));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
