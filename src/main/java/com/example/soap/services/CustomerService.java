package com.example.soap.services;

import com.example.soap.models.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public CustomerResponse getCustomerResponse() {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setApprovedAmount(1000);
        customerResponse.setCreditScore(758);
        customerResponse.setIsEligible(true);
        return customerResponse;
    }
}
