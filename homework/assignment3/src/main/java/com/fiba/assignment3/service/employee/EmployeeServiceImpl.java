package com.fiba.assignment3.service.employee;

import com.fiba.assignment3.entity.Employee;
import com.fiba.assignment3.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 07/11/2022 - 00:52
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Iterable<Employee> employees = employeeRepository.findAll();
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : employees) {
            employeeList.add(employee);
        }
        if (employeeList.isEmpty()) {
            throw new RuntimeException("No employees found");
        }
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }
        return employee.get();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("Employee cannot be null");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        if (employee == null) {
            throw new RuntimeException("Employee cannot be null");
        }
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        Employee employeeToUpdate = employeeRepository.findById(id).get();
        employeeToUpdate.setEmployeeName(employee.getEmployeeName());
        employeeToUpdate.setMonthlySalary(employee.getMonthlySalary());
        employeeToUpdate.setDepartment(employee.getDepartment());
        return employeeRepository.save(employeeToUpdate);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentId(Long departmentId) {
        List<Employee> employees = employeeRepository.findAllByDepartmentId(departmentId);
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees found");
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployeesBySalaryGreaterThan(double salary) {
        List<Employee> employees = employeeRepository.findAllByMonthlySalaryGreaterThan(salary);
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees found");
        }
        return employees;
    }
}
