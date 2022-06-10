package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.UserDTO;
import com.youthhomelessnessproject.academicsuccess.models.Admin;
import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.models.SurveyAdmin;
import com.youthhomelessnessproject.academicsuccess.services.AdminService;
import com.youthhomelessnessproject.academicsuccess.services.StudentService;
import com.youthhomelessnessproject.academicsuccess.services.SurveyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    // Runtime injection of StudentService, AdminService and SurveyAdminService dependencies
    private StudentService studentService;
    private final AdminService adminService;
    private final SurveyAdminService surveyAdminService;

    @Autowired
    public AdminController(StudentService studentService, AdminService adminService, SurveyAdminService surveyAdminService) {
        this.studentService = studentService;
        this.adminService = adminService;
        this.surveyAdminService = surveyAdminService;
    }

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        List<Student> students = studentService.getAllStudents();
        List<Admin> admins = adminService.getAllAdmins();
        List<SurveyAdmin> teachers = surveyAdminService.getAllSurveyAdmins();
        model.addAttribute("admin", ContextController.getAdmin());
        model.addAttribute("students", students);
        model.addAttribute("admins", admins);
        model.addAttribute("teachers", teachers);
        return "admin-dashboard";
    }

    @GetMapping("/admin/users")
    public String showAddUsersPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("admin", ContextController.getAdmin());
        model.addAttribute("userDto", userDTO);
        return "admin-add-user";
    }


    @GetMapping("/admin/edit/{id}")
    public String showAdminEditPage(@PathVariable Long id, Model model) {
        Admin admin = adminService.getAdminById(id);
        model.addAttribute("admin", admin);
        return "admin-admin-edit";
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
