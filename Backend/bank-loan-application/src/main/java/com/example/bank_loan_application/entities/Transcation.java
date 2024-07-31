package com.example.bank_loan_application.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="transcation")
@Data
public class Transcation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    int id;

    @ManyToOne
    @JoinColumn(name = "loanAccount_id", nullable = false)
    LoanAccount loan_id;

    @Column
    String typeOfTranscation;

    @Column
    double transcationAmount;

    @Column
    double reducedAmountBalance;

    @Column
    Date transcationDate;


}
