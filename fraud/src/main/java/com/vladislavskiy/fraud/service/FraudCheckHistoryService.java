package com.vladislavskiy.fraud.service;

import com.vladislavskiy.fraud.repository.FraudCheckHistoryRepository;
import com.vladislavskiy.fraud.model.FraudCheckHistory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckHistoryService {
    private FraudCheckHistoryRepository fraudCheckHistoryRepository;

    FraudCheckHistoryService(FraudCheckHistoryRepository fraudCheckHistoryRepository)
    {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
