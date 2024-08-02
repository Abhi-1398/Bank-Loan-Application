package com.example.bank_loan_application.repositories;

import com.example.bank_loan_application.entities.Customer;
import com.example.bank_loan_application.entities.LoanAccount;
import com.example.bank_loan_application.models.CustomerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    Customer save(Customer entity);

    @Override
    Optional<Customer> findById(Integer integer);

    Customer findByEmailAddress(@Param("email_address") String emailAddress);
}
