package com.example.bank_loan_application.entities;

import com.example.bank_loan_application.constants.TypeOfTranscation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="transcation")
@Data
@Getter
@Setter
@NoArgsConstructor
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
