package com.example.demo_jpa.repository;

import com.example.demo_jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // can two have same name
    // method ka return type
    List<Student> findByName(String name); // for simple task

    Optional<Student> findByEmail(String email);
    //Task find all the students whose name starting with same character
    // first write the sql for some task like this one
}
//save(S entity)
//findById(id)
//findAll()
//deleteById(id)
