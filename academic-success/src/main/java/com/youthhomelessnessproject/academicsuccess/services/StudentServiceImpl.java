package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    // Runtime constructor-based injection of StudentRepository dependency
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Student saveStudent(Student student) {
    	String encodedPassword = this.passwordEncoder.encode(student.getPassword());
    	student.setPassword(encodedPassword);
        return studentRepository.save(student);
    }

    public Student findStudentByUsernameAndPassword(String username) {
        Student student = studentRepository.findStudentByUsername(username);
        return student;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
    	String encodedPassword = this.passwordEncoder.encode(student.getPassword());
    	student.setPassword(encodedPassword);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
