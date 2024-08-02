package com.example.bank_loan_application.repositories;

import com.example.bank_loan_application.entities.Transcation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TranscationRepository extends CrudRepository<Transcation,Integer> {

    @Query(value = "SELECT * FROM Transcation WHERE loan_account_id = :loanAccountId ORDER BY transcation_date DESC LIMIT 1", nativeQuery = true)
    Transcation findLatestTransactionByLoanAccountId(@Param("loanAccountId") int loanAccountId);

    Transcation save(Transcation entity);

}
