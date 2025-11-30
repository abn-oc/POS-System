package com.posSystem.service;

import com.posSystem.models.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    Customer getCustomerById(Long id) throws Exception;
    List<Customer> getAllCustomers() throws Exception;
    List<Customer> searchCustomer(String keyword) throws Exception;
}
