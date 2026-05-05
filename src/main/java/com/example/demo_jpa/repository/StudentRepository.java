package com.example.demo_jpa.repository;

import com.example.demo_jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
//save(S entity)
//findById(id)
//findAll()
//deleteById(id)
