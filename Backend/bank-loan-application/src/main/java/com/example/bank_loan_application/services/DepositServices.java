package com.example.bank_loan_application.services;

import com.example.bank_loan_application.entities.LoanAccount;
import com.example.bank_loan_application.entities.Transcation;
import com.example.bank_loan_application.helpers.CalculateReducedInterestRate;
import com.example.bank_loan_application.models.DepositDetails;
import com.example.bank_loan_application.repositories.LoanAccountRepository;
import com.example.bank_loan_application.repositories.TranscationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositServices {

    @Autowired
    TranscationRepository transcationRepository;

    @Autowired
    LoanAccountRepository loanAccountRepository;

    @Transactional
    public void updateReducedInterestBalance(int loanId, double newBalance) {
        loanAccountRepository.updateReducedInterestBalance(newBalance, loanId);
    }
    public void saveDepositAmount(DepositDetails depositDetails){

        Transcation lastestTranscation = transcationRepository.findLatestTransactionByLoanAccountId(depositDetails.getLoan_id());
        LoanAccount loanAccount = loanAccountRepository.findById(depositDetails.getLoan_id()).get();

        Transcation transcation = new Transcation();

        double newReducedAmountBalance =  new CalculateReducedInterestRate().calculateInterestRateBalance(lastestTranscation,loanAccount,depositDetails.getDepositeDate())
                                        - depositDetails.getDepositAmount();

        transcation.setReducedAmountBalance(newReducedAmountBalance);
        transcation.setLoan_id(loanAccount);
        transcation.setTranscationAmount(depositDetails.getDepositAmount());
        transcation.setTypeOfTranscation("Deposit");

        transcationRepository.save(transcation);

        updateReducedInterestBalance(loanAccount.getId(),newReducedAmountBalance);
    }
}
