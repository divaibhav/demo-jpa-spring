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
import java.util.Optional;

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
        return convertToStudentDTO(saved);
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

    //Get ALL
    public List<StudentDTO> getAllStudents() {
        // call repository to get all the students
        List<Student> studentList = studentRepository.findAll();
        //convert to list of studentDTO
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : studentList) {
            studentDTOList.add(convertToStudentDTO(student));
        }
        //List<StudentDTO> list = studentList.stream().map(this::convertToStudentDTO).toList();
        return studentDTOList;
    }

    //get by id
    // either we will find the student or not

    public StudentDTO getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student student;
        if (studentOptional.isEmpty()) {
            student = null;
        } else {
            student = studentOptional.get();
        }
        // if student not null convert it or return null
        if (student != null) {
            return convertToStudentDTO(student);
        } else {
            return null;
        }
    }

    // add course
    public StudentDTO addCourse(Long id, CourseDTO courseDTO) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (!studentOptional.isEmpty()) {
            Student student = studentOptional.get();
            student.addCourse(new Course(courseDTO.getTitle()));
            Student saved = studentRepository.save(student);// how save is updating a student find out by yourself.
            return convertToStudentDTO(saved);
        } else {
            return null;
        }
    }

    //delete
    public String deleteStudent(Long id) {
//        // if return type is void
//        // void no information given to user
//        studentRepository.deleteById(id);

        // if I want to give some information to user, like student not found or deleted successfully
        //check if student exists
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student deleted Successfully";
        } else {
            return "Student Not Found";
        }
    }
}
