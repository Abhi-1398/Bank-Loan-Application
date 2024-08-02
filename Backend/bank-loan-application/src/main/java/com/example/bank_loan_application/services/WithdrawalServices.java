package com.example.bank_loan_application.services;

import com.example.bank_loan_application.entities.LoanAccount;
import com.example.bank_loan_application.entities.Transcation;
import com.example.bank_loan_application.helpers.CalculateReducedInterestRate;
import com.example.bank_loan_application.models.WithdrawalDetails;
import com.example.bank_loan_application.repositories.LoanAccountRepository;
import com.example.bank_loan_application.repositories.TranscationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawalServices {

    @Autowired
    TranscationRepository transcationRepository;

    @Autowired
    LoanAccountRepository loanAccountRepository;

    @Transactional
    public void updateReducedInterestBalance(int loanId, double newBalance) {
        loanAccountRepository.updateReducedInterestBalance(newBalance, loanId);
    }

    public void saveWithdrawal(WithdrawalDetails withdrawalDetails){
        Transcation lastestTranscation = transcationRepository.findLatestTransactionByLoanAccountId(withdrawalDetails.getLoan_id());
        LoanAccount loanAccount = loanAccountRepository.findById(withdrawalDetails.getLoan_id()).get();
        Transcation transcation = new Transcation();

        double newReducedAmountBalance =  new CalculateReducedInterestRate().calculateInterestRateBalance(lastestTranscation,loanAccount,withdrawalDetails.getWithdrawalDate())
                                          + withdrawalDetails.getWithdarwalAmount() ;

        transcation.setReducedAmountBalance(newReducedAmountBalance);
        transcation.setLoan_id(loanAccount);
        transcation.setTranscationAmount(withdrawalDetails.getWithdarwalAmount());
        transcation.setTypeOfTranscation("Withdraw");

        transcationRepository.save(transcation);

        updateReducedInterestBalance(loanAccount.getId(),newReducedAmountBalance);

    }
}
