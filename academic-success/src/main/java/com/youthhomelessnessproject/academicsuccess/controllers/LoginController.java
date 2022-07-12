package com.youthhomelessnessproject.academicsuccess.controllers;

import com.youthhomelessnessproject.academicsuccess.dto.ForgotPasswordDTO;
import com.youthhomelessnessproject.academicsuccess.models.Admin;
import com.youthhomelessnessproject.academicsuccess.models.Employee;
import com.youthhomelessnessproject.academicsuccess.models.Student;
import com.youthhomelessnessproject.academicsuccess.repositories.AdminRepository;
import com.youthhomelessnessproject.academicsuccess.repositories.EmployeeRepository;
import com.youthhomelessnessproject.academicsuccess.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();;

	@GetMapping("/admin")
	public String showAdminLoginForm(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "admin-login";
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

	@PostMapping("/admin")
	public String loginAdmin(@ModelAttribute("admin") Admin admin) {
		Admin savedAdmin = adminRepository.findAdminByUsername(admin.getUsername());
		if (savedAdmin != null) {
			ContextController.setAdmin(savedAdmin);
			if (bcryptEncoder.matches(admin.getPassword(), savedAdmin.getPassword())) {
				return "redirect:/admin/dashboard";
			}
		}
		System.out.println("Admin Login failed");
		return "redirect:/login/admin?error";
	}

	@PostMapping("/student")
	public String loginStudent(@ModelAttribute("student") Student student) {
		Student savedStudent = studentRepository.findStudentByUsername(student.getUsername());
		if (savedStudent != null) {
			ContextController.setStudent(savedStudent);
			
			if (bcryptEncoder.matches(student.getPassword(), savedStudent.getPassword())) {
				return "redirect:/student/dashboard";
			}
		}
		System.out.println("Student Login failed");
		return "redirect:/login/student?error";

	}

	@PostMapping("/employee")
	public String loginEmployee(@ModelAttribute("employee") Employee employee) {
		Employee savedEmployee = employeeRepository.findEmployeeByUsername(employee.getUsername());
		if (savedEmployee != null) {
			ContextController.setEmployee(savedEmployee);
			if (bcryptEncoder.matches(employee.getPassword(), savedEmployee.getPassword())) {
				return "redirect:/employee/dashboard";
			}
		}
		System.out.println("Employee Login failed");
		return "redirect:/login/employee?error";

	}

	@GetMapping("/reset")
	public String showForgotPasswordForm(Model model) {
		ForgotPasswordDTO forgotPasswordDto = new ForgotPasswordDTO();
		model.addAttribute("forgotPasswordDto", forgotPasswordDto);
		return "forgot-password";
	}

	@PostMapping("/reset")
	public String resetStudentPassword(@ModelAttribute ForgotPasswordDTO forgotPasswordDto) {
		if (!forgotPasswordDto.getNewPassword().equals(forgotPasswordDto.getNewConfirmPassword())) {
			return "redirect:/reset?mismatch";
		}
		Student student = studentRepository.findStudentByUsername(forgotPasswordDto.getUsername());
		if (student == null)
			return "redirect:/reset?errorusername";
		student.setPassword(bcryptEncoder.encode(forgotPasswordDto.getNewPassword()));
		studentRepository.save(student);
		return "redirect:/login/student";
	}

}
