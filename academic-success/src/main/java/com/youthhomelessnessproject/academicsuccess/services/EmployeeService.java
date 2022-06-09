package com.youthhomelessnessproject.academicsuccess.services;

import com.youthhomelessnessproject.academicsuccess.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    // CREATE
    Employee saveEmployee(Employee employee);

    // READ
    Optional<Employee> getEmployeeByUsername(String username);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();

    // UPDATE
    Employee updateEmployee(Employee employee);

    // DELETE
    void deleteEmployeeById(Long id);
}
