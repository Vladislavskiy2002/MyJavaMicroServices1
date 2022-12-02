package com.vladislavskiy.fraud.controller;

import com.vladislavskiy.fraud.service.FraudCheckHistoryService;
import com.vladislavskiy.fraud.FraudCheckResponce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/fraud-check")
public class FraudCheckHistoryController {

    final private FraudCheckHistoryService fraudCheckHistoryService;

    public FraudCheckHistoryController(FraudCheckHistoryService fraudCheckHistoryService) {
        log.info("FraudCheckHistoryController is opened");
        this.fraudCheckHistoryService = fraudCheckHistoryService;
    }

    @GetMapping(path = "{customerId}")
    public FraudCheckResponce isFraudster(@PathVariable("customerId") Integer customerId)
    {
        log.info("isFraudster is opened");
        boolean isFraudulentCustomer = fraudCheckHistoryService.isFraudulentCustomer(customerId);
        return new FraudCheckResponce(isFraudulentCustomer);
    }

}
