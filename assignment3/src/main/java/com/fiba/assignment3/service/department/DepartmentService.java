package com.fiba.assignment3.service.department;

import com.fiba.assignment3.entity.Department;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 07/11/2022 - 00:51
 */
public interface DepartmentService {
    Department getDepartmentById(Long id);

    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department updateDepartment(Long id, Department department);

    void deleteDepartment(Long id);
}
