package com.vladislavskiy.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistationRequest customerRegistationRequest)
    {
        log.info("new customer registration{}", customerRegistationRequest);
        customerService.registerCustomer(customerRegistationRequest);

    }
}
