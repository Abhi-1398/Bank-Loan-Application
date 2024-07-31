package com.example.bank_loan_application.repositories;

import com.example.bank_loan_application.entities.Customer;
import com.example.bank_loan_application.models.CustomerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    Customer save(Customer entity);
}
