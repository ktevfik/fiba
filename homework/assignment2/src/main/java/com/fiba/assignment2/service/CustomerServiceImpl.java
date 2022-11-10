package com.fiba.assignment2.service;

import com.fiba.assignment2.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 00:38
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = new Customer(id, "Tevfik Kadan", 1000);
        return customer;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "Tevfik Kadan", 1000));
        customers.add(new Customer(2L, "John Doe", 2000));
        customers.add(new Customer(3L, "Jane Doe", 3000));
        return customers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setCustomerId(300L);
        System.out.println("Customer created: " + customer.getCustomerId() + " " + customer.getCustomerName() + " " + customer.getTotalDebit());
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        System.out.println("Customer updated: " + customer.getCustomerId() + " " + customer.getCustomerName() + " " + customer.getTotalDebit());
    }

    @Override
    public void deleteCustomer(Long id) {
        System.out.println("Customer deleted: " + id);
    }
}

