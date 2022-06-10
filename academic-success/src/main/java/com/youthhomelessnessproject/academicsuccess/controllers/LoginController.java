package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.models.Admin;
import com.youthhomelessnessproject.academicsuccess.models.Employee;
import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.models.SurveyAdmin;
import com.youthhomelessnessproject.academicsuccess.repositories.AdminRepository;
import com.youthhomelessnessproject.academicsuccess.repositories.EmployeeRepository;
import com.youthhomelessnessproject.academicsuccess.repositories.StudentRepository;
import com.youthhomelessnessproject.academicsuccess.repositories.SurveyAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SurveyAdminRepository surveyAdminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/admin")
    public String showAdminLoginForm(Model model) {
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "admin-login";
    }


    // TODO verify that survey-admin property works in survey-admin-login.html
    // TODO for that matter, verify all teacher -> survey-admin stuff works!
    @GetMapping("/survey-admin")
    public String showSurveyAdminLoginForm(Model model) {
        SurveyAdmin surveyAdmin = new SurveyAdmin();
        model.addAttribute("survey-admin", surveyAdmin);
        return "survey-admin-login";
    }

    @GetMapping("/student")
    public String showStudentLoginForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student-login";
    }

    @GetMapping("/employee")
    public String showEmployeeLoginForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-login";
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
    public String loginSurveyAdmin(@ModelAttribute("survey-admin") SurveyAdmin surveyAdmin) {
        SurveyAdmin savedSurveyAdmin = surveyAdminRepository.findSurveyAdminByUsername(surveyAdmin.getUsername());
        if(savedSurveyAdmin != null) {
            ContextController.setSurveyAdmin(savedSurveyAdmin);
            if(savedSurveyAdmin.getPassword().equalsIgnoreCase(surveyAdmin.getPassword())) {
                return "redirect:/survey-admin/dashboard";
            }
        }
        System.out.println("Survey Admin Login failed");
        return "redirect:/login/survey-admin?error";
    }

    @PostMapping("/employee")
    public String loginEmployee(@ModelAttribute("employee") Employee employee) {
        Employee savedEmployee = employeeRepository.findEmployeeByUsername(employee.getUsername());
        if(savedEmployee != null) {
            ContextController.setEmployee(savedEmployee);
            if(savedEmployee.getPassword().equalsIgnoreCase(employee.getPassword())) {
                return "redirect:/employee/dashboard";
            }
        }
        System.out.println("Employee Login failed");
        return "redirect:/login/employee?error";

    }


}
