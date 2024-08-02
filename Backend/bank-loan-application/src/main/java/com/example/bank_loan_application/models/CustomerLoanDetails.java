package com.example.bank_loan_application.models;

import com.example.bank_loan_application.constants.LoanType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CustomerLoanDetails {

    int customerId;

    int loan_id;

    LoanType loanType;

    double loanInterest;

    double loanAmount;

    double remainingReducedInterestBalance;

    boolean isAccountActive;
}
