package com.fiba.assignment1.controller;

import com.fiba.assignment1.entity.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 00:03
 */
@Controller
public class EmployeeController {

    @GetMapping("/employee/get")
    @ResponseBody
    public String getEmployee() {
        long employeeId = 1L;
        String url = "http://localhost:8080/api/v1/employee/" + employeeId;
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = restTemplate.getForObject(url, Employee.class);
        System.out.println("Employee: " + employee);
        return "Employee get method applied successfully: " + "ID: " + employee.getId() + " Employee name: " + employee.getEmployeeName() + " Employee monthly salary: " + employee.getMonthlySalary();
    }

    @GetMapping("/employee/post")
    @ResponseBody
    public String createEmployee() {
        String url = "http://localhost:8080/api/v1/employee";
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = new Employee(1L, "Tevfik Kadan", 1000);
        Employee createdEmployee = restTemplate.postForObject(url, employee, Employee.class);
        System.out.println("Employee created: " + createdEmployee);
        return "Employee post method applied successfully: " + "ID: " + employee.getId() + " Employee name: " + employee.getEmployeeName() + " Employee monthly salary: " + employee.getMonthlySalary();
    }

    @GetMapping("/employee/put")
    @ResponseBody
    public String updateEmployee() {
        String url = "http://localhost:8080/api/v1/employee";
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = new Employee(2L, "Dilek Kadan", 1500);
        restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Employee>(employee), Void.class);
        System.out.println("Employee updated: " + employee);
        return "Employee put method applied successfully: " + "ID: " + employee.getId() + " Employee name: " + employee.getEmployeeName() + " Employee monthly salary: " + employee.getMonthlySalary();
    }

    @GetMapping("/employee/delete")
    @ResponseBody
    public String deleteEmployee() {
        long employeeId = 1L;
        String url = "http://localhost:8080/api/v1/employee/" + employeeId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
        System.out.println("Employee deleted: " + employeeId);
        return "Employee delete method applied successfully: " + "ID: " + employeeId;
    }

}
