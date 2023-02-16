package com.example.soap.endpoints;

import com.example.soap.models.CustomerRequest;
import com.example.soap.models.CustomerResponse;
import com.example.soap.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Endpoint
public class LoanEndPoint {
    private static final String NAMESPACE = "http://www.example.com/soap/models";
    private CustomerService customerService;

    @Autowired
    public LoanEndPoint(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "CustomerRequest")
    //Here "localPart" should match with input request - See @XMLRootElement annotation under CustomerRequest.java
    @ResponsePayload
    public CustomerResponse getCustomerResponse(@RequestPayload CustomerRequest customerRequest) {
        return customerService.getCustomerResponse();
    }

}
