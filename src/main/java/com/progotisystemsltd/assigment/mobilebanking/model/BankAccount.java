package com.progotisystemsltd.assigment.mobilebanking.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANK_ACCOUNT_ID")
    private Integer bankAccountId;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNT_NAME", nullable = false)
    private String accountName;

    @Column(name = "MOBILE_PHONE_NUMBER", nullable = false)
    private String mobilePhoneNumber;

    @OneToOne(fetch= FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "ACCOUNT_TYPE_ID", nullable = false)
    private AccountType accountType;

    @Column(name = "BALANCE")
    private Double balance = 0.0;

    public BankAccount() {
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

 }
