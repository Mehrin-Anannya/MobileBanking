package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;
import com.progotisystemsltd.assigment.mobilebanking.repository.AccountNumberRepository;
import com.progotisystemsltd.assigment.mobilebanking.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService{
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AccountNumberRepository accountNumberRepository;

    @Override
    public void createBankAccount(BankAccount bankAccount) {
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<BankAccount> getAllBankAccount() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getBankAccountById(Integer bankAccountId) {
        return bankAccountRepository.findById(bankAccountId).orElse(null);
    }

    @Override
    public BankAccount getBankAccountByAccountNumber(Long accountNumber) {
        return bankAccountRepository.findBankAccountByAccountNumber(accountNumber);
    }

    @Override
    public BankAccount checkBalanceWithMPN(String mobilePhoneNumber) {
        return bankAccountRepository.findBalanceByMobilePhoneNumber(mobilePhoneNumber);
    }

}
