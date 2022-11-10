package com.fiba.assignment3.resource;

import com.fiba.assignment3.entity.Employee;
import com.fiba.assignment3.service.employee.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 07/11/2022 - 00:58
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employees/department/{departmentId}")
    public List<Employee> getAllEmployeesByDepartmentId(@PathVariable("departmentId") Long departmentId) {
        return employeeService.getAllEmployeesByDepartmentId(departmentId);
    }

    @GetMapping("/employees/high-salary/{salary}")
    public List<Employee> getEmployeesBySalaryGreaterThan(@PathVariable("salary") Double salary) {
        return employeeService.getEmployeesBySalaryGreaterThan(salary);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
