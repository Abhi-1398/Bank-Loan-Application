package com.example.bank_loan_application.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class WithdrawalDetails {
    int loan_id;
    int customer_id;
    double withdarwalAmount;
    Date withdrawalDate;
}
