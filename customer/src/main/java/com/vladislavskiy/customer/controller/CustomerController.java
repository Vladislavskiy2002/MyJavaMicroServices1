package com.vladislavskiy.customer.controller;

import com.vladislavskiy.customer.CustomerRegistationRequest;
import com.vladislavskiy.customer.repository.CustomerRepository;
import com.vladislavskiy.customer.FraudCheckResponce;
import com.vladislavskiy.customer.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public CustomerController(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistationRequest customerRegistationRequest) {

        Customer customer = Customer.builder()
                .firstName(customerRegistationRequest.firstName())
                .lastName(customerRegistationRequest.lastName())
                .email(customerRegistationRequest.email())
                .id(customerRegistationRequest.id())
                .build();
        System.out.println(customer);
        customerRepository.saveAndFlush(customer);
        log.info("customerRepository is saved");

        log.info("try to restTemplate");

        FraudCheckResponce fraudCheckResponce = restTemplate.getForObject(
                "http://localhost:2221/api/v1/fraud-check/{customerId}",
                FraudCheckResponce.class,
                customer.getId());
        if (fraudCheckResponce.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }
}
