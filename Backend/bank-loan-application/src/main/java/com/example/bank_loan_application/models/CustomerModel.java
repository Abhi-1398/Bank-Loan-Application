package com.example.bank_loan_application.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class CustomerModel {
    private String name;
    private String mobileNumber;
    private String address;
    private String emailAddress;

    public CustomerModel(String address, String name, String mobileNumber, String emailAddress) {
        this.address = address;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }
}