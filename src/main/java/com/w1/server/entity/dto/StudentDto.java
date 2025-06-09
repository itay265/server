package com.w1.server.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public class StudentDto {

    private Long id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Positive(message = "Age must be positive")
    private double age;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    private String email;

    private StudentDetailsDto details;

    private Set<Long> teacherIds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public double getAge() { return age; }
    public void setAge(double age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public StudentDetailsDto getDetails() { return details; }
    public void setDetails(StudentDetailsDto details) { this.details = details; }

    public Set<Long> getTeacherIds() { return teacherIds; }
    public void setTeacherIds(Set<Long> teacherIds) { this.teacherIds = teacherIds; }
}
