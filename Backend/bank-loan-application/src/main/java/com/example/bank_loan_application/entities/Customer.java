package com.example.bank_loan_application.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Table(name = "customers")
@Entity
@Data
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    int id ;

    @Column
    String name;

    @Column(nullable = false)
    String mobileNumber;

    @Column
    String Address;

    @Column(nullable = false)
    String emailAddress;

}
