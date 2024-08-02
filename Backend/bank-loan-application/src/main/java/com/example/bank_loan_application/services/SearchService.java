package com.example.bank_loan_application.services;

import com.example.bank_loan_application.entities.Customer;
import com.example.bank_loan_application.entities.LoanAccount;
import com.example.bank_loan_application.models.CustomerLoanDetails;
import com.example.bank_loan_application.repositories.CustomerRepository;
import com.example.bank_loan_application.repositories.LoanAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LoanAccountRepository loanAccountRepository;

    public CustomerLoanDetails searchDetails(String value , String typeOfValue){

        CustomerLoanDetails customerLoanDetails = null;

        switch (typeOfValue){
            case "customer_id" :
                customerLoanDetails = getCustomerDetailsByCustomerId(value);
                break;
            case "loan_id" :
                customerLoanDetails = getCustomerDetailsByLoanId(value);
                break;
            case "mobileNumber" :
                customerLoanDetails = getCustomerDetailsByMobileNumber(value);
                break;
        }
        return customerLoanDetails;
    }

    CustomerLoanDetails getCustomerDetailsByCustomerId(String value){
        CustomerLoanDetails customerLoanDetails =null;
        Optional<Customer> customer = customerRepository.findById(Integer.parseInt(value));
        LoanAccount loanAccount = loanAccountRepository.findByCustomerId(Integer.parseInt(value));
        if(customer.isPresent()){
            customerLoanDetails.setCustomerId(customer.get().getId());
            customerLoanDetails.setLoanAmount(loanAccount.getLoanAmount());
            customerLoanDetails.setLoanInterest(loanAccount.getInterest_rate());
            customerLoanDetails.setAccountActive(loanAccount.isActive());
            customerLoanDetails.setLoanType(loanAccount.getLoanType());
            customerLoanDetails.setLoan_id(loanAccount.getId());
            customerLoanDetails.setRemainingReducedInterestBalance(loanAccount.getReducedInterestBalance());
        }else{
            new Exception("Customer Not Found, Please enter Correct customer_id");
        }
        return customerLoanDetails;
    }

    CustomerLoanDetails getCustomerDetailsByLoanId(String value){
        CustomerLoanDetails customerLoanDetails =null;
        Optional<LoanAccount> loanAccount = loanAccountRepository.findById(Integer.parseInt(value));
        if(loanAccount.isPresent()){
            customerLoanDetails.setCustomerId(loanAccount.get().getCustomer().getId());
            customerLoanDetails.setLoanAmount(loanAccount.get().getLoanAmount());
            customerLoanDetails.setLoanInterest(loanAccount.get().getInterest_rate());
            customerLoanDetails.setAccountActive(loanAccount.get().isActive());
            customerLoanDetails.setLoanType(loanAccount.get().getLoanType());
            customerLoanDetails.setLoan_id(loanAccount.get().getId());
            customerLoanDetails.setRemainingReducedInterestBalance(loanAccount.get().getReducedInterestBalance());
        }else{
            new Exception("Loan Not Found, Please enter Correct loan_id");
        }
        return customerLoanDetails;
    }

    CustomerLoanDetails getCustomerDetailsByMobileNumber(String value){
        CustomerLoanDetails customerLoanDetails =null;
        Customer customer = customerRepository.findByEmailAddress(value);
        LoanAccount loanAccount = loanAccountRepository.findByCustomerId(Integer.parseInt(value));

        customerLoanDetails.setCustomerId(customer.getId());
        customerLoanDetails.setLoanAmount(loanAccount.getLoanAmount());
        customerLoanDetails.setLoanInterest(loanAccount.getInterest_rate());
        customerLoanDetails.setAccountActive(loanAccount.isActive());
        customerLoanDetails.setLoanType(loanAccount.getLoanType());
        customerLoanDetails.setLoan_id(loanAccount.getId());
        customerLoanDetails.setRemainingReducedInterestBalance(loanAccount.getReducedInterestBalance());

        return customerLoanDetails;
    }
}
