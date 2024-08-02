package com.example.bank_loan_application.repositories;

import com.example.bank_loan_application.entities.LoanAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanAccountRepository extends CrudRepository<LoanAccount,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE loan_account SET reduced_interest_balance = :balance WHERE id = :loanId", nativeQuery = true)
    void updateReducedInterestBalance(@Param("balance") double balance, @Param("loanId") int loanId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE loan_account SET is_active = FALSE WHERE id = :loanId", nativeQuery = true)
    void deactivateLoanAccount(@Param("loanId") int loanId);

    LoanAccount save(LoanAccount entity);

    LoanAccount findByCustomerId(@Param("customerId") int customerId);
}
