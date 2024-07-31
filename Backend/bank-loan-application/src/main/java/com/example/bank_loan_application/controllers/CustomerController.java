package com.example.bank_loan_application.controllers;

import com.example.bank_loan_application.entities.Customer;
import com.example.bank_loan_application.models.CustomerModel;
import com.example.bank_loan_application.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping("/api/addCustomer")
    public String saveCustomer(@RequestBody Customer customer){
        String message = "Customer Saved Succesfully";
        System.out.println(customer);
        service.saveCustomer(customer);
        return message;
    }


}
