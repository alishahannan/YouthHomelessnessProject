package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.UserDTO;
import com.youthhomelessnessproject.academicsuccess.models.Admin;
import com.youthhomelessnessproject.academicsuccess.models.Employee;
import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.services.AdminService;
import com.youthhomelessnessproject.academicsuccess.services.EmployeeService;
import com.youthhomelessnessproject.academicsuccess.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;


    // If admin not found, NullPointerException (error 500) is thrown by
    // admin.firstName on each page. This redirects to 403 instead of 500 page
    public String checkIsAdmin(Model model, String page) {
        try {
            Admin admin = adminService.getAdminById(ContextController.getAdmin().getId());
            model.addAttribute("admin", admin);
            return page;
        } catch (Exception e) {
            return "/error/403";
        }
    }


    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        List<Student> students = studentService.getAllStudents();
        List<Admin> admins = adminService.getAllAdmins();
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("students", students);
        model.addAttribute("admins", admins);
        model.addAttribute("employees", employees);
        return checkIsAdmin(model, "admin-dashboard");
    }

    @GetMapping("/admin/users")
    public String showAddUsersPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDto", userDTO);
        return checkIsAdmin(model, "admin-add-user");
    }

    @GetMapping("/admin/edit/{id}")
    public String showAdminEditPage(@PathVariable Long id, Model model) {
        Admin admin = adminService.getAdminById(id);
        model.addAttribute("admin", admin);
        try {
            // Check if admin logged in
            ContextController.getAdmin().getId();
            return "admin-admin-edit";
        } catch (Exception e) {
            return "/error/403";
        }

    }

    @PostMapping("/admin/edit/{id}")
    public String updateAdminDetails(@PathVariable Long id, @ModelAttribute("admin") Admin admin,
                                     Model model) {
        Admin existingAdmin = adminService.getAdminById(id);
        existingAdmin.setFirstName(admin.getFirstName());
        existingAdmin.setLastName(admin.getLastName());
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setPassword(admin.getPassword());
        adminService.saveAdmin(existingAdmin);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteAdminDetails(@PathVariable Long id,
                                     Model model) {
        List<Admin> admins = adminService.getAllAdmins();
        if(admins.size() == 1) {
            return "redirect:/admin/dashboard?adminerror";
        }
        adminService.deleteAdminById(id);
        return "redirect:/admin/dashboard";
    }

}
