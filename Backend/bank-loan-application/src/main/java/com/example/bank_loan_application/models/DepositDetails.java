package com.example.bank_loan_application.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class DepositDetails {
    int loan_id;
    int customer_id;
    double depositAmount;
    Date depositeDate;
}
