package com.fiba.assignment3.repository;

import com.fiba.assignment3.entity.Department;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Tevfik Kadan
 * @created 07/11/2022 - 00:50
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
