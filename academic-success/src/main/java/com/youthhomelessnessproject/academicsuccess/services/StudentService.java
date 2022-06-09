package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Student;

import java.util.List;

public interface StudentService {

    // CREATE
    Student saveStudent(Student student);

    // READ
    Student getStudentById(Long id);
    List<Student> getAllStudents();

    // UPDATE
    Student updateStudent(Student student);

    // DELETE
    void deleteStudentById(Long id);

}
