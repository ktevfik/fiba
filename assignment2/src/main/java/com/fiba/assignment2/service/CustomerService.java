package com.fiba.assignment2.service;

import com.fiba.assignment2.entity.Customer;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 00:37
 */
public interface CustomerService {

    Customer getCustomerById(Long id);

    List<Customer> getCustomers();

    Customer createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Long id);
}
