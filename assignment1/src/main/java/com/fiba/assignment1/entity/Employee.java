package com.fiba.assignment1.entity;

/**
 * @author Tevfik Kadan
 * @created 05/11/2022 - 23:56
 */
public class Employee {

    private Long id;
    private String employeeName;
    private double monthlySalary;

    public Employee() {
    }

    public Employee(Long id, String employeeName, double monthlySalary) {
        this.id = id;
        this.employeeName = employeeName;
        this.monthlySalary = monthlySalary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
}
