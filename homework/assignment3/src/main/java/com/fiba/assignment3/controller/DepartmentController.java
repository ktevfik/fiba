package com.fiba.assignment3.controller;

import com.fiba.assignment3.entity.Department;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tevfik Kadan
 * @created 07/11/2022 - 01:15
 */

@Controller
public class DepartmentController {

    @GetMapping("/departments/get/all")
    @ResponseBody
    public String getAllDepartments() {
        String url = "http://localhost:8080/api/v1/departments";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Department[]> responseEntity = restTemplate.getForEntity(url, Department[].class);
        List<Department> departments = List.of(Objects.requireNonNull(responseEntity.getBody()));
        if (departments.isEmpty()) {
            return "No departments found";
        }
        StringBuilder sb = new StringBuilder();
        for (Department department : departments) {
            sb.append("Department found: ID: ").append(department.getDepartmentId()).append(" Name: ").append(department.getDepartmentName());
        }
        return sb.toString();
    }

    @GetMapping("/departments/get/{id}")
    @ResponseBody
    public String getDepartmentById(@PathVariable("id") Long id) {
        String url = "http://localhost:8080/api/v1/departments/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(url, Department.class);
        Department department = responseEntity.getBody();
        if (department == null) {
            return "No department found";
        }
        return "Department found: ID: " + department.getDepartmentId() + " Name: " + department.getDepartmentName();
    }

    @GetMapping("/departments/save")
    @ResponseBody
    public String saveDepartment() {
        String url = "http://localhost:8080/api/v1/departments";
        Department department = new Department();
        department.setDepartmentName("IT");
        department.setEmployees(new ArrayList<>());
        RestTemplate restTemplate = new RestTemplate();
        Department savedDepartment = restTemplate.postForObject(url, department, Department.class);

        if (savedDepartment == null) {
            return "No department saved";
        }
        return "Department saved: ID: " + savedDepartment.getDepartmentId() + " Name: " + savedDepartment.getDepartmentName();
    }

    @GetMapping("/departments/update/{id}")
    @ResponseBody
    public String updateDepartment(@PathVariable("id") Long id) {
        String url = "http://localhost:8080/api/v1/departments/" + id;
        Department department = new Department();
        department.setDepartmentName("Department 2");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Department>(department), Void.class);
        return "Department updated: ID: " + id + " Name: " + department.getDepartmentName();
    }

    @GetMapping("/departments/delete/{id}")
    @ResponseBody
    public String deleteDepartment(@PathVariable("id") Long id) {
        String url = "http://localhost:8080/api/v1/departments/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
        return "Department deleted: ID: " + id;
    }
}
