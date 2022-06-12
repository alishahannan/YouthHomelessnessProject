package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.models.Session;
import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.services.SessionService;
import com.youthhomelessnessproject.academicsuccess.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    // Runtime injection of StudentService dependency
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    // Only students can register
    @GetMapping("/register/new")
    public ModelAndView showStudentRegistrationForm() {
        Student student = new Student();
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("student", student);

        return mav;
    }
    @PostMapping("/students")
    public String createStudent(@ModelAttribute("student") Student student) {
        student.setRole("student");
        studentService.saveStudent(student);
        return "redirect:/login/student";
    }


    @GetMapping("/student/dashboard")
    public String showStudentDashboard(Model model) {
        model.addAttribute("student", ContextController.getStudent());
        return "student-dashboard";
    }


    //ADMIN ACTIONS ON STUDENTS ENITITY*****
    @GetMapping("/student/edit/{id}")
    public String showUpdateStudentPage(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "admin-student-edit";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id, Model model) {
        studentService.deleteStudentById(id);
        return "redirect:/admin/dashboard";
    }

    //Update existing student
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        // TODO REMOVE FIRST / LAST FROM EVERY PLACE
        //  WHERE STUDENT IS MENTIONED / EDITED
//        existingStudent.setFirstName(student.getFirstName());
//        existingStudent.setLastName(student.getLastName());
        existingStudent.setUsername(student.getUsername());
        existingStudent.setPassword(student.getPassword());
        studentService.updateStudent(existingStudent);
        return "redirect:/admin/dashboard";
    }

}
