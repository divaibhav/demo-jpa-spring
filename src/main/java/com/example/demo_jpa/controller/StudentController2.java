package com.example.demo_jpa.controller;

import com.example.demo_jpa.dto.CourseDTO;
import com.example.demo_jpa.dto.StudentDTO;
import com.example.demo_jpa.service.StudentService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/s1")
public class StudentController2 {

    @Autowired
    private StudentService1 service1;

    @PostMapping
    public StudentDTO create(@RequestBody StudentDTO dto) {
        return service1.createStudent(dto);
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service1.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Long id) {
        return service1.getStudentById(id);
    }

    //adding course
    @PostMapping("/{id}/courses")
    public StudentDTO addCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return service1.addCourse(id, courseDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service1.deleteStudent(id);
    }

    // think how to remove a course


}
