package com.example.demo_jpa.service;

import com.example.demo_jpa.entity.Student;
import com.example.demo_jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    //save student
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    // get all student
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // get student by id
    public Optional<Student> getStudentById(Long id) {
        return repository.findById(id);
    }

    // delete student
    public void deleteStudent(Long id) {
        repository.deleteById(id);
        // food for the brain
        // what if this student id is referred in some other table?
    }

    //List<Student> findByName(String name);
    public List<Student> getAllByName(String name) {
        return repository.findByName(name);
    }
    // find the student by name
    //http://localhost:8080/students/search?name=value
//    @GetMapping("/search")
//    public List<Student> getAllByName(@RequestParam String name){
//        return service.getAllByName(name);
//    }

    // find by email
    public Optional<Student> getByEmail(String email) {
        return repository.findByEmail(email);
    }


}
