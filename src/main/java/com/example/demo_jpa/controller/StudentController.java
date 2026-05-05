package com.example.demo_jpa.controller;

import com.example.demo_jpa.entity.Student;
import com.example.demo_jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        Optional<Student> studentById = service.getStudentById(id);

        if (studentById.isEmpty()) {
            return null;
        } else {
            return studentById.get();
        }
    }

    @DeleteMapping("/{id}")
    private String delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return "Deleted Successfully";
    }


}
