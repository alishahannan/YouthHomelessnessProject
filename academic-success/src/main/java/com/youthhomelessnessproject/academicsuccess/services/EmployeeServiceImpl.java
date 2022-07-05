package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Employee;
import com.youthhomelessnessproject.academicsuccess.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    // Runtime constructor-based injection of EmployeeRepository dependency
    private final EmployeeRepository employeeRepository;
    
    
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
    	String encodedPassword = this.passwordEncoder.encode(employee.getPassword());
    	employee.setPassword(encodedPassword);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findEmployeeByUsername(username);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
    	String encodedPassword = this.passwordEncoder.encode(employee.getPassword());
    	employee.setPassword(encodedPassword);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

}
