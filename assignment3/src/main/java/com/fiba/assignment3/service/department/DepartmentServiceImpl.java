package com.fiba.assignment3.service.department;

import com.fiba.assignment3.entity.Department;
import com.fiba.assignment3.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 07/11/2022 - 00:52
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {

            throw new RuntimeException("Department not found");
        }
        return department.get();
    }

    @Override
    public Department saveDepartment(Department department) {
        if (department == null) {
            throw new RuntimeException("Department cannot be null");
        }
        departmentRepository.save(department);
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        Iterable<Department> departments = departmentRepository.findAll();
        List<Department> departmentList = new ArrayList<>();
        for (Department department : departments) {
            departmentList.add(department);
        }
        if (departmentList.isEmpty()) {
            throw new RuntimeException("No departments found");
        }
        return departmentList;
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        if (department == null) {
            throw new RuntimeException("Department cannot be null");
        }
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found");
        }
        Department departmentToUpdate = departmentRepository.findById(id).get();
        departmentToUpdate.setDepartmentName(department.getDepartmentName());

        return departmentRepository.save(departmentToUpdate);
    }

    @Override
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found");
        }
        departmentRepository.deleteById(id);
    }
}
