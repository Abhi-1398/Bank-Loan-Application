package com.example.bank_loan_application.models;

import com.example.bank_loan_application.constants.LoanType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class LoanAccountDetails {

    private int customer_id;

    private LoanType loanType;

    private float interest_rate;

    private double loanAmount;

    private double reducedInterestBalance;

    private Date loanDate;

    private boolean isActive;

}
