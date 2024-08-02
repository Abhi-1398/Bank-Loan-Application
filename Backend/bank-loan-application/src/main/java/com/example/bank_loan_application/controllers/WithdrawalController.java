package com.example.bank_loan_application.controllers;

import com.example.bank_loan_application.models.DepositDetails;
import com.example.bank_loan_application.models.WithdrawalDetails;
import com.example.bank_loan_application.services.WithdrawalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawalController {

    @Autowired
    WithdrawalServices withdrawalServices;

    @PostMapping("/api/withdrawal")
    public String saveDeposit(@RequestBody WithdrawalDetails withdrawalDetails){
        String message = "Withdrawal Succesfully";
        withdrawalServices.saveWithdrawal(withdrawalDetails);
        return message;
    }
}
