package com.example.bank_loan_application.services;

import com.example.bank_loan_application.entities.LoanAccount;
import com.example.bank_loan_application.entities.Transcation;
import com.example.bank_loan_application.models.TranscationDetails;
import com.example.bank_loan_application.repositories.TranscationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranscationService {

    @Autowired
    TranscationRepository repo;

    public void saveNewTranscation(TranscationDetails transcationDetails, LoanAccount loanAccount){

        Transcation transcation = new Transcation();

        transcation.setTranscationAmount(transcationDetails.getTranscationAmount());
        transcation.setTranscationDate(transcationDetails.getTranscationDate());
        transcation.setTypeOfTranscation(transcationDetails.getTypeOfTranscation());
        transcation.setLoan_id(loanAccount);
        transcation.setReducedAmountBalance(transcationDetails.getReducedAmountBalance());

        repo.save(transcation);
    }

    public void saveTranscation(){
        Transcation transcation = new Transcation();
        repo.save(transcation);
    }
}
