package com.example.bank_loan_application.controllers;

import com.example.bank_loan_application.models.LoanAccountDetails;
import com.example.bank_loan_application.services.LoanAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class LoanAccountController {

    @Autowired
    LoanAccountService loanAccountService;

    @PostMapping("/api/addLoanAccount")
    public String saveLoanAccount(@RequestBody LoanAccountDetails accDetails){
        String message = "LoanAccount Saved Succesfully";
        try {
            loanAccountService.saveLoanAccount(accDetails);
        }catch (Exception ex){
            message = "Customer Not found, Send Active Customer";
        }
        return message;
    }

    @PutMapping("/deactivateLoan")
    public ResponseEntity<String> deactivateLoan(
            @RequestParam int loanId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date closingDate) {
        loanAccountService.deactivateLoanAccount(loanId, closingDate);
        return ResponseEntity.ok("Loan account deactivated successfully");
    }
}
