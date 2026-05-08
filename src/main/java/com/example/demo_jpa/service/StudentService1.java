package com.example.demo_jpa.service;

import com.example.demo_jpa.dto.CourseDTO;
import com.example.demo_jpa.dto.StudentDTO;
import com.example.demo_jpa.entity.Course;
import com.example.demo_jpa.entity.Student;
import com.example.demo_jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService1 {

    @Autowired
    private StudentRepository studentRepository;

    // create student with courses
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = new Student(dto.getName(), dto.getEmail());
        // add courses to student
        if (dto.getCourses() != null) {
            //traverse
            for (CourseDTO c : dto.getCourses()) {
                student.addCourse(new Course(c.getTitle()));
            }
        }
        // save the student in db
        Student saved = studentRepository.save(student);

    }

    private StudentDTO convertToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        // old school traverse and create
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : student.getCourses()) {
            courseDTOList.add(new CourseDTO(course.getId(), course.getTitle()));
        }
        studentDTO.setCourses(courseDTOList);

        // new way lambda
//        List<CourseDTO> courseDTOList1 = student.getCourses()
//                .stream()
//                .map(c -> new CourseDTO(c.getId(), c.getTitle()))
//                .toList();
        return studentDTO;
    }
}
