package com.example.bank_loan_application.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="loanAccount")
@Data
public class LoanAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @Column
    String loanType;

    @Column
    float interest_rate;

    @Column
    double loanAmount;

    @Column
    double reducedInterestBalance;

    @Column
    Date loanDate;

    @Column
    boolean isActive;

}
