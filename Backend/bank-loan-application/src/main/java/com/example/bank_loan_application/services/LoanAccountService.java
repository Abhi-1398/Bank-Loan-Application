package com.example.bank_loan_application.services;

import com.example.bank_loan_application.constants.TypeOfTranscation;
import com.example.bank_loan_application.entities.Customer;
import com.example.bank_loan_application.entities.LoanAccount;
import com.example.bank_loan_application.entities.Transcation;
import com.example.bank_loan_application.helpers.CalculateReducedInterestRate;
import com.example.bank_loan_application.models.LoanAccountDetails;
import com.example.bank_loan_application.models.TranscationDetails;
import com.example.bank_loan_application.repositories.CustomerRepository;
import com.example.bank_loan_application.repositories.LoanAccountRepository;
import com.example.bank_loan_application.repositories.TranscationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class LoanAccountService {

    @Autowired
    LoanAccountRepository loanAccountRepository;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    TranscationService transcationService;

    @Autowired
    TranscationRepository transcationRepository;

    public LoanAccount saveLoanAccount(LoanAccountDetails accountDetails) throws Exception {

        Optional<Customer> customerDetails = customerRepo.findById(accountDetails.getCustomer_id());

        if(!customerDetails.isPresent()){
            throw new Exception("Employee Not Found");
        }

        LoanAccount acc = new LoanAccount(
                    customerDetails.get(),
                    accountDetails.getLoanType(),
                    accountDetails.getInterest_rate(),
                    accountDetails.getLoanAmount(),
                    accountDetails.getReducedInterestBalance(),
                    accountDetails.getLoanDate(),
                    accountDetails.isActive()
        );

        LoanAccount loanAccount = loanAccountRepository.save(acc);

        TranscationDetails transcationDetails = new TranscationDetails();

        transcationDetails.setLoan_id(loanAccount.getId());
        transcationDetails.setTranscationAmount(acc.getLoanAmount());
        transcationDetails.setTranscationDate(acc.getLoanDate());
        transcationDetails.setReducedAmountBalance(acc.getLoanAmount());
        transcationDetails.setTypeOfTranscation(TypeOfTranscation.Deposit.toString());

        transcationService.saveNewTranscation(transcationDetails,loanAccount);

        return loanAccount;
    }

    @Transactional
    public void deactivateLoanAccount(int loanId, Date closingDate) {
        LoanAccount loanAccount = loanAccountRepository.findById(loanId).get();

        Transcation lastestTranscation = transcationRepository.findLatestTransactionByLoanAccountId(loanId);

        TranscationDetails transcationDetails = new TranscationDetails();

        double newReducedAmountBalance =  new CalculateReducedInterestRate().calculateInterestRateBalance(lastestTranscation,loanAccount,closingDate);

        transcationDetails.setLoan_id(loanAccount.getId());
        transcationDetails.setTranscationAmount(newReducedAmountBalance);
        transcationDetails.setTranscationDate(closingDate);
        transcationDetails.setReducedAmountBalance(0);
        transcationDetails.setTypeOfTranscation(TypeOfTranscation.Deposit.toString());

        transcationService.saveNewTranscation(transcationDetails,loanAccount);

        loanAccountRepository.deactivateLoanAccount(loanId);
    }
}
