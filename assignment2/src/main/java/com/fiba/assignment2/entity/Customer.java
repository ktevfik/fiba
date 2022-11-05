package com.fiba.assignment2.entity;

/**
 * @author Tevfik Kadan
 * @created 05/11/2022 - 00:15
 */
public class Customer {

    private Long customerId;
    private String customerName;
    private double totalDebit;

    public Customer() {
    }

    public Customer(Long customerId, String customerName, double totalDebit) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalDebit = totalDebit;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }
}
