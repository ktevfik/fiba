package com.fiba.assignment1.resources;

import com.fiba.assignment1.entity.Employee;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 05/11/2022 - 23:57
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeResource {

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = new Employee(id, "Tevfik Kadan", 1000);
        return employee;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "Tevfik Kadan", 1000));
        employees.add(new Employee(2L, "John Doe", 2000));
        employees.add(new Employee(3L, "Jane Doe", 3000));
        return employees;
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(300L);
        System.out.println("Employee created: " + employee.getId() + " " + employee.getEmployeeName() + " " + employee.getMonthlySalary());
        return employee;
    }

    @PutMapping("/employee")
    public void updateEmployee(@RequestBody Employee employee) {
        System.out.println("Employee updated: " + employee.getId() + " " + employee.getEmployeeName() + " " + employee.getMonthlySalary());
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        System.out.println("Employee deleted: " + id);
    }

}
