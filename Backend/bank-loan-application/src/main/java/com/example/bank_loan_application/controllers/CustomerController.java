package com.example.bank_loan_application.controllers;

import com.example.bank_loan_application.entities.Customer;
import com.example.bank_loan_application.models.CustomerModel;
import com.example.bank_loan_application.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("/api/getCustomer")
    public Iterable<Customer> getAllCustomer(){
        return service.getAllCustomer();
    }

    @PostMapping("/api/addCustomer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){
        String message = "Customer Saved Succesfully";
        service.saveCustomer(customer);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
