package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.*;
import com.progotisystemsltd.assigment.mobilebanking.repository.AccountTypeRepository;
import com.progotisystemsltd.assigment.mobilebanking.repository.BankAccountRepository;
import com.progotisystemsltd.assigment.mobilebanking.repository.BusinessAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService{
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private BusinessAccountRepository businessAccountRepository;

    @Override
    public String createBankAccount(BankAccountInfo bankAccountInfo, Model model) {
        String message;
        BankAccount bankAccount;
        AccountType accountType;
        //Check whether there is already an account with the given mobile phone number
        bankAccount = bankAccountRepository.findBankAccountByMobilePhoneNumber(bankAccountInfo.getBankAccount().getMobilePhoneNumber());
        //Create New Bank Account
        if(bankAccount == null) {
            accountType = accountTypeRepository.findAccountTypeByAccountTypeName(AccountTypeNames.BUSINESS.name());
            //Create Business Account Entry
            if (bankAccountInfo.getBankAccount().getAccountType().getAccountTypeId() == accountType.getAccountTypeId()){
                BusinessAccount businessAccountInfo = bankAccountInfo.getBusinessAccountInfo();
                businessAccountInfo.setBankAccount(bankAccountInfo.getBankAccount());
                businessAccountRepository.save(businessAccountInfo);
            }else{
                //Create New Bank Account with Account Type 'Personal'
                bankAccountRepository.save(bankAccountInfo.getBankAccount());
            }
            bankAccount = bankAccountRepository.findBankAccountByMobilePhoneNumber(bankAccountInfo.getBankAccount().getMobilePhoneNumber());
            message = "Account opened successfully. Your Account Number is:" +bankAccount.getAccountNumber();
        }else {
            message = "Already have an account with this mobile number";
        }
        model.addAttribute("message", message);
        return message;
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
        return bankAccountRepository.findBankAccountByMobilePhoneNumber(mobilePhoneNumber);
    }
    @Override
    public BankAccount updateBankAccount(BankAccount bankAccount){
        BankAccount bankAccountInfo;
        try {
            bankAccountInfo = bankAccountRepository.findBankAccountByMobilePhoneNumber(bankAccount.getMobilePhoneNumber());
            AccountType accountType = accountTypeRepository.findAccountTypeByAccountTypeName(AccountTypeNames.PERSONAL.name());
            if(bankAccountInfo.getAccountNumber() !=null && bankAccountInfo.getAccountType().getAccountTypeId()== accountType.getAccountTypeId()){
                bankAccountInfo.setBalance(bankAccountInfo.getBalance() + bankAccount.getBalance());
                bankAccountRepository.save(bankAccountInfo);
            }
            return bankAccountInfo;
        }
        catch (Exception e){
            bankAccountInfo = new BankAccount();
            return bankAccountInfo;
        }
    }
}
