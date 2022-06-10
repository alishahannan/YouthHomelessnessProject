package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.UserDTO;
import com.youthhomelessnessproject.academicsuccess.models.Admin;
import com.youthhomelessnessproject.academicsuccess.models.Employee;
import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.models.SurveyAdmin;
import com.youthhomelessnessproject.academicsuccess.services.AdminService;
import com.youthhomelessnessproject.academicsuccess.services.EmployeeService;
import com.youthhomelessnessproject.academicsuccess.services.StudentService;
import com.youthhomelessnessproject.academicsuccess.services.SurveyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    // Runtime injection of StudentService, EmployeeService,
    // AdminService and SurveyAdminService dependencies
    private final StudentService studentService;
    private final EmployeeService employeeService;
    private final AdminService adminService;
    private final SurveyAdminService surveyAdminService;

    @Autowired
    public RegistrationController(StudentService studentService, EmployeeService employeeService, AdminService adminService, SurveyAdminService surveyAdminService) {
        this.studentService = studentService;
        this.employeeService = employeeService;
        this.adminService = adminService;
        this.surveyAdminService = surveyAdminService;
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute UserDTO userDTO) {
        if (userDTO.getRole().equalsIgnoreCase("student")) {
            Student student = new Student();
            student.setUsername(userDTO.getUsername());
            student.setPassword(userDTO.getPassword());
            studentService.saveStudent(student);
        } else if (userDTO.getRole().equalsIgnoreCase("employee")) {
            Employee employee = new Employee();
            employee.setFirstName(userDTO.getFirstName());
            employee.setLastName(userDTO.getLastName());
            employee.setUsername(userDTO.getUsername());
            employee.setPassword(userDTO.getPassword());
            employeeService.saveEmployee(employee);
        } else if (userDTO.getRole().equalsIgnoreCase("admin")) {
            Admin admin = new Admin();
            admin.setFirstName(userDTO.getFirstName());
            admin.setLastName(userDTO.getLastName());
            admin.setUsername(userDTO.getUsername());
            admin.setPassword(userDTO.getPassword());
            adminService.saveAdmin(admin);
        } else if (userDTO.getRole().equalsIgnoreCase("survey-admin")) {
            SurveyAdmin surveyAdmin = new SurveyAdmin();
            surveyAdmin.setFirstName(userDTO.getFirstName());
            surveyAdmin.setLastName(userDTO.getLastName());
            surveyAdmin.setUsername(userDTO.getUsername());
            surveyAdmin.setPassword(userDTO.getPassword());
            surveyAdminService.saveSurveyAdmin(surveyAdmin);
        }
        return "redirect:/admin/dashboard";
    }

}


