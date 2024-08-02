package com.example.bank_loan_application.helpers;

import com.example.bank_loan_application.entities.LoanAccount;
import com.example.bank_loan_application.entities.Transcation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalculateReducedInterestRate {

    public double calculateInterestRateBalance(Transcation transcation, LoanAccount loanAccount, Date depositDate){

        double interestRate = transcation.getReducedAmountBalance();

        if( depositDate.compareTo(transcation.getTranscationDate()) == 0 ){
            return interestRate;
        };

        int transactionDay = transcation.getTranscationDate().getDate();
        int transactionMonth = transcation.getTranscationDate().getMonth()+1;
        int transactionYear = transcation.getTranscationDate().getYear();

        int depositDay = depositDate.getDate();
        int depositMonth = depositDate.getMonth()+1;
        int depositYear = depositDate.getMonth();

        if(depositYear == transactionYear){

            if(transactionMonth == depositMonth){
                interestRate =  sameMonth(
                       loanAccount.getInterest_rate(),
                       Math.abs(transactionDay-depositDay),
                       transcation.getReducedAmountBalance()
                      );
            }else if((transactionMonth+1) == depositMonth){
                interestRate =  nextMonth(
                       loanAccount.getInterest_rate(),
                       transcation.getTranscationDate(),
                       depositDate,
                       transcation.getReducedAmountBalance()
                      );
            }else{
                interestRate = nextToLaterMonth(
                        loanAccount.getInterest_rate(),
                        transcation.getTranscationDate(),
                        depositDate,
                        transcation.getReducedAmountBalance()
                );
            }

        }else{
            interestRate = nextYear(loanAccount.getInterest_rate(),
                    transcation.getTranscationDate(),
                    depositDate,
                    transcation.getReducedAmountBalance()
            );
        }
        return interestRate;
    }

    public double nextYear(float interestRate , Date transactionDate , Date depositDate , double lastBalance ){
        double interest = 0;
        interest += ( ( ( transactionDate.getDate()-getLastDateOfMonth(transactionDate)) *interestRate*lastBalance) / 36500 );
        lastBalance+=interest;
        Date nextMonth = getNextMonth(transactionDate);
        for(int i = 1; i < noOfMonths(transactionDate,depositDate) ; i++){
            interest += ( ( ( transactionDate.getDate()-getLastDateOfMonth(nextMonth)) *interestRate*lastBalance) / 36500 );
            lastBalance+=interest;
            nextMonth = getNextMonth(nextMonth);
        };
        interest += ( ( depositDate.getDate() * interestRate * lastBalance ) / 3600 );
        return interest;
    }

    public double sameMonth(float interestRate,int dateDiff,double lastBalance){
        return (dateDiff*interestRate*lastBalance)/36500;
    };

    public double nextMonth( float interestRate , Date transactionDate , Date depositDate , double lastBalance ){
        double interest = 0;
        interest += ( ( ( transactionDate.getDate()-getLastDateOfMonth(transactionDate)) *interestRate*lastBalance) / 36500 );
        lastBalance+=interest;
        interest += ( ( depositDate.getDate() * interestRate * lastBalance ) / 3600 );
        return interest;
    };

    public double nextToLaterMonth(float interestRate , Date transactionDate , Date depositDate , double lastBalance ){
        double interest = 0;
        interest += ( ( ( transactionDate.getDate()-getLastDateOfMonth(transactionDate)) *interestRate*lastBalance) / 36500 );
        lastBalance+=interest;
        Date nextMonth = getNextMonth(transactionDate);
        for(int i = 1; i < ( depositDate.getMonth() - transactionDate.getMonth() ) ; i++){
            interest += ( ( ( transactionDate.getDate()-getLastDateOfMonth(nextMonth)) *interestRate*lastBalance) / 36500 );
            lastBalance+=interest;
            nextMonth = getNextMonth(nextMonth);
        };
        interest += ( ( depositDate.getDate() * interestRate * lastBalance ) / 3600 );
        return interest;
    };

    public int noOfMonths(Date transactionDate , Date depositDate){
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(transactionDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(depositDate);

        int startYear = startCalendar.get(Calendar.YEAR);
        int startMonth = startCalendar.get(Calendar.MONTH);

        int endYear = endCalendar.get(Calendar.YEAR);
        int endMonth = endCalendar.get(Calendar.MONTH);

        int yearDifference = endYear - startYear;
        int monthDifference = endMonth - startMonth;

        return yearDifference * 12 + monthDifference;
    }
    public int getLastDateOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.add(Calendar.DAY_OF_MONTH, -1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return Integer.parseInt(sdf.format(calendar.getTime()));
    }

    public Date getNextMonth(Date curDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }
}
