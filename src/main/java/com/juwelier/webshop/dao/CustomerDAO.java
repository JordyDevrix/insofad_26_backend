package com.juwelier.webshop.dao;

import com.juwelier.webshop.models.Customer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDAO {

    private final CustomerRepository customerRepository;

    public CustomerDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllUsers() {
        return this.customerRepository.findAll();
    }

    public Customer getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return customerRepository.findByEmail(email);
    }
}