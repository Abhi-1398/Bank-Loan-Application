package com.example.bank_loan_application.entities;

import com.example.bank_loan_application.constants.LoanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="loanAccount")
@Data
@Getter
@Setter
public class LoanAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    int id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanType loanType;

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

    public LoanAccount(Customer customer, LoanType loanType, float interest_rate, double loanAmount, double reducedInterestBalance, Date loanDate, boolean isActive) {
        this.customer = customer;
        this.loanType = loanType;
        this.interest_rate = interest_rate;
        this.loanAmount = loanAmount;
        this.reducedInterestBalance = reducedInterestBalance;
        this.loanDate = loanDate;
        this.isActive = isActive;
    }
}
