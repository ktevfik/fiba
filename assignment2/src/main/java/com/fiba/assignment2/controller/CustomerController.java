package com.fiba.assignment2.controller;

import com.fiba.assignment2.entity.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 00:18
 */
@Controller
public class CustomerController {

    @GetMapping("/customer/get")
    @ResponseBody
    public String getCustomer() {
        long customerId = 1L;
        String url = "http://localhost:8080/api/v1/customer/" + customerId;
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.getForObject(url, Customer.class);
        System.out.println("Customer: " + customer);
        return "Customer get method applied successfully: " + "ID: " + customer.getCustomerId() + " Customer name: " + customer.getCustomerName() + " Customer total debit: " + customer.getTotalDebit();
    }

    @GetMapping("/customer/post")
    @ResponseBody
    public String createCustomer() {
        String url = "http://localhost:8080/api/v1/customer";
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = new Customer(1L, "Tevfik Kadan", 1000);
        Customer createdCustomer = restTemplate.postForObject(url, customer, Customer.class);
        System.out.println("Customer created: " + createdCustomer);
        return "Customer post method applied successfully: " + "ID: " + customer.getCustomerId() + " Customer name: " + customer.getCustomerName() + " Customer total debit: " + customer.getTotalDebit();
    }

    @GetMapping("/customer/put")
    @ResponseBody
    public String updateCustomer() {
        String url = "http://localhost:8080/api/v1/customer";
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = new Customer(2L, "Dilek Kadan", 1500);
        restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<Customer>(customer), Void.class);
        System.out.println("Customer updated: " + customer);
        return "Customer put method applied successfully: " + "ID: " + customer.getCustomerId() + " Customer name: " + customer.getCustomerName() + " Customer total debit: " + customer.getTotalDebit();
    }

    @GetMapping("/customer/delete")
    @ResponseBody
    public String deleteCustomer() {
        long customerId = 1L;
        String url = "http://localhost:8080/api/v1/customer/" + customerId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
        System.out.println("Customer deleted: " + customerId);
        return "Customer delete method applied successfully: " + "ID: " + customerId;
    }

}
