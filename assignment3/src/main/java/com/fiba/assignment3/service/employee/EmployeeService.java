package com.fiba.assignment3.service.employee;

import com.fiba.assignment3.entity.Employee;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 07/11/2022 - 00:51
 */
public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    List<Employee> getAllEmployeesByDepartmentId(Long departmentId);

    List<Employee> getEmployeesBySalaryGreaterThan(double salary);
}
