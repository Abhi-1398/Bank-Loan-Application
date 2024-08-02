package com.example.bank_loan_application.controllers;

import com.example.bank_loan_application.models.CustomerLoanDetails;
import com.example.bank_loan_application.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtherAPIController {

    @Autowired
    SearchService searchService;

    @GetMapping("/api/searchDetails")
    public CustomerLoanDetails searchDetails(
            @RequestParam String value,
            @RequestParam String typeOfValue
    ){
        return searchService.searchDetails(value,typeOfValue);
    }

}
