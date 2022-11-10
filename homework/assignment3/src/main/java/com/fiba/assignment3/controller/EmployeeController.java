package com.fiba.assignment3.controller;

import com.fiba.assignment3.entity.Department;
import com.fiba.assignment3.entity.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @author Tevfik Kadan
 * @created 07/11/2022 - 01:34
 */
@Controller
public class EmployeeController {

    @GetMapping("/employees/get/all")
    @ResponseBody
    public String getAllEmployees() {
        String url = "http://localhost:8080/api/v1/employees";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
        List<Employee> employees = List.of(Objects.requireNonNull(responseEntity.getBody()));
        if (employees.isEmpty()) {
            return "No employees found";
        }
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            sb.append("Employee found: ID: ").append(employee.getEmployeeId()).append(" Name: ").append(employee.getEmployeeName());
        }
        return sb.toString();
    }

    @GetMapping("/employees/get/{id}")
    @ResponseBody
    public String getEmployeeById(@PathVariable("id") Long id) {
        String url = "http://localhost:8080/api/v1/employees/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url, Employee.class);
        Employee employee = responseEntity.getBody();
        if (employee == null) {
            return "No employee found";
        }
        return "Employee found: ID: " + employee.getEmployeeId() + " Name: " + employee.getEmployeeName();
    }

    @GetMapping("/employees/departments/{id}")
    @ResponseBody
    public String getEmployeesByDepartmentId(@PathVariable("id") Long id) {
        String url = "http://localhost:8080/api/v1/employees/department/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
        List<Employee> employees = List.of(Objects.requireNonNull(responseEntity.getBody()));
        if (employees.isEmpty()) {
            return "No employees found";
        }
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            sb.append("Employee found: ID: ").append(employee.getEmployeeId()).append(" Name: ").append(employee.getEmployeeName());
        }
        return sb.toString();
    }

    @GetMapping("/employees/high-salary/{salary}")
    @ResponseBody
    public String getEmployeesBySalaryGreaterThan(@PathVariable("salary") Double salary) {
        String url = "http://localhost:8080/api/v1/employees/high-salary/" + salary;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
        List<Employee> employees = List.of(Objects.requireNonNull(responseEntity.getBody()));
        if (employees.isEmpty()) {
            return "No employees found";
        }
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            sb.append("Employee found: ID: ").append(employee.getEmployeeId()).append(" Name: ").append(employee.getEmployeeName());
        }
        return sb.toString();
    }

    @GetMapping("/employees/add")
    @ResponseBody
    public String addEmployee() {
        String url = "http://localhost:8080/api/v1/employees";
        RestTemplate restTemplate = new RestTemplate();
        Department department = new Department();
        department.setDepartmentId(0L);
        department.setDepartmentName("IT");

        Employee employee = new Employee();
        employee.setEmployeeName("Tevfik Kadan");
        employee.setMonthlySalary(10000.0);
        employee.setEmployeeId(0L);
        employee.setDepartment(department);
        ResponseEntity<Employee> responseEntity = restTemplate.postForEntity(url, employee, Employee.class);
        Employee employee1 = responseEntity.getBody();
        if (employee1 == null) {
            return "Employee could not be added";
        }
        return "Employee added: ID: " + employee1.getEmployeeId() + " Name: " + employee1.getEmployeeName();
    }

    @GetMapping("/employees/update/{id}")
    @ResponseBody
    public String updateEmployee(@PathVariable("id") Long id) {
        String url = "http://localhost:8080/api/v1/employees/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Department department = new Department();
        department.setDepartmentId(0L);
        department.setDepartmentName("IT");

        Employee employee = new Employee();
        employee.setEmployeeName("Tevfik Kadan");
        employee.setMonthlySalary(10000.0);
        employee.setEmployeeId(0L);
        employee.setDepartment(department);
        restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Employee>(employee), Void.class);
        return "Employee updated: ID: " + employee.getEmployeeId() + " Name: " + employee.getEmployeeName();
    }

    @GetMapping("/employees/delete/{id}")
    @ResponseBody
    public String deleteEmployee(@PathVariable("id") Long id) {
        String url = "http://localhost:8080/api/v1/employees/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
        return "Employee deleted";
    }

}
