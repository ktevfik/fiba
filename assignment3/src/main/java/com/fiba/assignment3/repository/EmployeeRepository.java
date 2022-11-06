package com.fiba.assignment3.repository;

import com.fiba.assignment3.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 07/11/2022 - 00:50
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e from Employee e where e.department.departmentId = :departmentId")
    List<Employee> findAllByDepartmentId(@Param("departmentId") long departmentId);

    @Query("SELECT e FROM Employee as e where e.monthlySalary >= :highSalary")
    List<Employee> findAllByMonthlySalaryGreaterThan(@Param("highSalary") double highSalary);


}
