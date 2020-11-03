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
    private Long accountNumber;
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
    @Column(name = "MOBILE_PHONE_NUMBER")
    private String mobilePhoneNumber;
    @Column(name = "ACCOUNT_TYPE_ID")
    private Integer accountTypeId;
    @Column(name = "BALANCE")
    private Double balance;

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
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

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
