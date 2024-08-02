package com.example.bank_loan_application.models;

import com.example.bank_loan_application.entities.LoanAccount;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class TranscationDetails {

    int loan_id;

    String typeOfTranscation;

    double transcationAmount;

    double reducedAmountBalance;

    Date transcationDate;

}
