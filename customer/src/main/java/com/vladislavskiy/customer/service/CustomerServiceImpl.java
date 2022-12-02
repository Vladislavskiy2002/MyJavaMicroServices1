package com.vladislavskiy.customer.service;

import com.vladislavskiy.customer.CustomerRegistationRequest;
import com.vladislavskiy.customer.repository.CustomerRepository;
import com.vladislavskiy.customer.model.Customer;
import org.springframework.stereotype.Service;

@Service
public record CustomerServiceImpl(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.save(customer);
    }
}
