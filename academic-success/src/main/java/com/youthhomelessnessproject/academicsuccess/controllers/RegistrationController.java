package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.UserDTO;
import com.youthhomelessnessproject.academicsuccess.models.Admin;
import com.youthhomelessnessproject.academicsuccess.models.Employee;
import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.services.AdminService;
import com.youthhomelessnessproject.academicsuccess.services.EmployeeService;
import com.youthhomelessnessproject.academicsuccess.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AdminService adminService;
   
    @Autowired
	  private PasswordEncoder bcryptEncoder;
    


    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute UserDTO userDto) {
        if (userDto.getRole().equalsIgnoreCase("student")) {
            Student student = new Student();
            student.setUsername(userDto.getUsername());
            student.setPassword(userDto.getPassword());
            studentService.saveStudent(student);
        } else if (userDto.getRole().equalsIgnoreCase("employee")) {
            Employee employee = new Employee();
            employee.setFirstName(userDto.getFirstName());
            employee.setLastName(userDto.getLastName());
            employee.setUsername(userDto.getUsername());
            employee.setPassword(userDto.getPassword());
            employeeService.saveEmployee(employee);
        } else if (userDto.getRole().equalsIgnoreCase("admin")) {
            Admin admin = new Admin();
            admin.setFirstName(userDto.getFirstName());
            admin.setLastName(userDto.getLastName());
            admin.setUsername(userDto.getUsername());
            admin.setPassword(userDto.getPassword());
            adminService.saveAdmin(admin);
        }
        return "redirect:/admin/dashboard";
    }

}


