package com.progotisystemsltd.assigment.mobilebanking.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

import javax.persistence.*;


@Entity
@SequenceGenerator(sequenceName = "hibernate_seq", initialValue = 1, allocationSize = 1000000000, name="seq")
@Table(name = "BANK_ACCOUNT")
public class BankAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANK_ACCOUNT_ID")
    private Integer bankAccountId;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq")
    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber;

    @Column(name = "ACCOUNT_NAME", nullable = false)
    private String accountName;

    @Column(name = "MOBILE_PHONE_NUMBER", nullable = false)
    private String mobilePhoneNumber;

    @OneToOne(fetch= FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "ACCOUNT_TYPE_ID", nullable = false)
    private AccountType accountType;

    @Column(name = "BALANCE")
    private Double balance;

    public BankAccount() {
    }

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

    public BankAccount(Integer bankAccountId, Long accountNumber, String accountName, String mobilePhoneNumber, AccountType accountType, Double balance) {
        this.bankAccountId = bankAccountId;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.accountType = accountType;
        this.balance = balance;
    }
}
