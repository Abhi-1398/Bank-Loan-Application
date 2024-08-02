package com.example.bank_loan_application.controllers;

import com.example.bank_loan_application.models.DepositDetails;
import com.example.bank_loan_application.services.DepositServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {

    @Autowired
    DepositServices depositService;

    @PostMapping("/api/deposit")
    public String saveDeposit(@RequestBody DepositDetails depositDetails){
        String message = "Deposited Succesfully";
        depositService.saveDepositAmount(depositDetails);
        return message;
    }

}
