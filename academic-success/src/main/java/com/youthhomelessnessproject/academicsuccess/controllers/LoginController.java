package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.models.Admin;
import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.models.SurveyAdmin;
import com.youthhomelessnessproject.academicsuccess.repositories.AdminRepository;
import com.youthhomelessnessproject.academicsuccess.repositories.StudentRepository;
import com.youthhomelessnessproject.academicsuccess.services.AdminService;
import com.youthhomelessnessproject.academicsuccess.services.StudentService;
import com.youthhomelessnessproject.academicsuccess.services.SurveyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    // Runtime injection of StudentRepository, AdminRepository and SurveyAdminService dependencies
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final SurveyAdminService surveyAdminService;

    @Autowired
    public LoginController(StudentRepository studentRepository, AdminRepository adminRepository, SurveyAdminService surveyAdminService) {
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
        this.surveyAdminService = surveyAdminService;
    }

    @GetMapping("/admin")
    public String showAdminLoginForm(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "admin-login";
    }

    @GetMapping("/survey-admin")
    public String showSurveyAdminLoginForm(Model model) {
        SurveyAdmin surveyAdmin = new SurveyAdmin();
        model.addAttribute("survey-admin", surveyAdmin);
        return "survey-admin-login";
        // TODO verify that survey-admin property works in survey-admin-login.html
        // TODO for that matter, verify all teacher -> survey-admin stuff works!
    }

    @GetMapping("/student")
    public String showStudentLoginForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-login";
    }

    @PostMapping("/student")
    public String loginStudent(@ModelAttribute("student") Student student) {
        Student savedStudent = studentRepository.findStudentByUsername(student.getUsername());
        if(savedStudent != null) {
            ContextController.setStudent(savedStudent);
            if(savedStudent.getPassword().equalsIgnoreCase(student.getPassword())) {
                return "redirect:/student/dashboard";
            }
        }
        System.out.println("Student Login failed");
        return "redirect:/login/student?error";
    }

    @PostMapping("/admin")
    public String loginAdmin(@ModelAttribute("admin") Admin admin) {
        Admin savedAdmin = adminRepository.findAdminByUsername(admin.getUsername());
        if(savedAdmin != null) {
            ContextController.setAdmin(savedAdmin);
            if(savedAdmin.getPassword().equalsIgnoreCase(admin.getPassword())) {
                return "redirect:/admin/dashboard";
            }
        }
        System.out.println("Admin Login failed");
        return "redirect:/login/admin?error";
    }

    @PostMapping("/survey-admin")
    public String loginSurveyAdmin(@ModelAttribute("teacher") SurveyAdmin surveyAdmin) {
        SurveyAdmin savedSurveyAdmin = surveyAdminService.getSurveyAdminByUsername(surveyAdmin.getUsername());
        if(savedSurveyAdmin != null) {
            ContextController.setSurveyAdmin(savedSurveyAdmin);
            if(savedSurveyAdmin.getPassword().equalsIgnoreCase(surveyAdmin.getPassword())) {
                return "redirect:/survey-admin/dashboard";
            }
        }
        System.out.println("Teacher Login failed");
        return "redirect:/login/teacher?error";
    }


}
