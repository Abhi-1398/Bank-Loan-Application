package com.example.bank_loan_application.services;

import com.example.bank_loan_application.entities.Customer;
import com.example.bank_loan_application.models.CustomerModel;
import com.example.bank_loan_application.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer saveCustomer(Customer customer){
        return repository.save(customer);
    }

    public Iterable<Customer> getAllCustomer(){ return repository.findAll(); }
}
