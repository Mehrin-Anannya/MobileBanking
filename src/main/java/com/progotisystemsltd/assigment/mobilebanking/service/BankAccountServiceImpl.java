package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.*;
import com.progotisystemsltd.assigment.mobilebanking.repository.AccountTypeRepository;
import com.progotisystemsltd.assigment.mobilebanking.repository.BankAccountRepository;
import com.progotisystemsltd.assigment.mobilebanking.repository.BusinessAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private BusinessAccountRepository businessAccountRepository;

    @Override
    public String createBankAccount(BankAccountInfo bankAccountInfo, Model model) {
        String message ="";
        BankAccount bankAccount;
        AccountType accountType;
        //Check whether there is already an account with the given mobile phone number
        bankAccount = bankAccountRepository.findBankAccountByMobilePhoneNumber(bankAccountInfo.getBankAccount().getMobilePhoneNumber());
        //Create New Bank Account
        if (bankAccount == null) {
            //Find Account Type
            accountType = accountTypeRepository.findById(bankAccountInfo.getBankAccount().getAccountType().getAccountTypeId()).orElse(null);
            String accountNumber = getAccountNumber(accountType);
            bankAccountInfo.getBankAccount().setAccountNumber(accountNumber);
            //Create New Bank Account
            bankAccount = bankAccountRepository.save(bankAccountInfo.getBankAccount());
            //Create Business Account Entry
            if (accountType.getAccountTypeName().equals(AccountTypeNames.BUSINESS.name())) {
                BusinessAccount businessAccountInfo = bankAccountInfo.getBusinessAccountInfo();
                businessAccountInfo.setBankAccount(bankAccountInfo.getBankAccount());
                businessAccountRepository.save(businessAccountInfo);
            }
            message = "Account opened successfully. Your Account Number is:" + bankAccountInfo.getBankAccount().getAccountNumber();
        } else {
            message = "Already have an account with this mobile number";
        }
        model.addAttribute("message", message);
        return message;
    }
    public String getAccountNumber(AccountType accountType){
        BankAccount bankAccount = bankAccountRepository.findTopByAccountTypeOrderByAccountNumberDesc(accountType.getAccountTypeId());
        String accountNumber = bankAccount.getAccountNumber();
        Integer i;
        String delimiters = "-";
        // analyzing the string
        String[] tokensVal = accountNumber.split(delimiters);
        tokensVal[1] = "-0" + String.valueOf(Integer.parseInt(tokensVal[1]) + 1);
        return tokensVal[0] + tokensVal[1];
    }

    @Override
    public List<BankAccount> getAllBankAccount() {
        return bankAccountRepository.findAll();
    }


    @Override
    @Transactional
    public String getTransferMoney(TransferMoney transferMoney) {
        String transferMoneyMessage = "";

        BankAccount senderBankAccount = bankAccountRepository.findBankAccountByAccountNumber(transferMoney.getSenderBankAccount().getAccountNumber().trim());
        if(senderBankAccount.getAccountType().getAccountTypeName().equals(AccountTypeNames.PERSONAL.name())) {
            BankAccount receiverBankAccount = bankAccountRepository.findBankAccountByAccountNumber(transferMoney.getReceiverBankAccount().getAccountNumber().trim());
            //Money can be sent from sender's bank account by keeping the minimum balance 500
            if(senderBankAccount.getBalance() == null)
                transferMoneyMessage = "Sorry, your balance is zero.";
            else if(senderBankAccount.getBalance() - transferMoney.getTransferAmount() > 500) {
                //transferring money
                senderBankAccount.setBalance(senderBankAccount.getBalance() - transferMoney.getTransferAmount());
                receiverBankAccount.setBalance(receiverBankAccount.getBalance() + transferMoney.getTransferAmount());
                bankAccountRepository.save(senderBankAccount);
                bankAccountRepository.save(receiverBankAccount);
                transferMoneyMessage = "Amount transferred successfully";
            }else{
                transferMoneyMessage = "Sorry, money cannot be transferred from your account because it exceeds minimum balance";
            }
        }else
            transferMoneyMessage = "Sorry, money cannot be transferred from Business account";
        return transferMoneyMessage;
    }

    @Override
    public BankAccount checkBalanceWithMPN(String mobilePhoneNumber) {
        return bankAccountRepository.findBankAccountByMobilePhoneNumber(mobilePhoneNumber);
    }

    @Override
    public String updateBankAccount(BankAccount bankAccount) {
        String message;
        BankAccount bankAccountInfo;
        bankAccountInfo = bankAccountRepository.findBankAccountByMobilePhoneNumber(bankAccount.getMobilePhoneNumber());
        if (bankAccountInfo == null) {
            message = "The account doesn't exist. ";
        } else {
            AccountType accountType = accountTypeRepository.findAccountTypeByAccountTypeName(AccountTypeNames.PERSONAL.name());
            if (bankAccountInfo.getAccountType().getAccountTypeId() == accountType.getAccountTypeId()) {
                //if balance is null
                if (bankAccountInfo.getBalance() == null) {
                    bankAccountInfo.setBalance(0.00);
                }
                //save adding previous balance and new balance
                bankAccountInfo.setBalance(bankAccountInfo.getBalance() + bankAccount.getBalance());
                bankAccountRepository.save(bankAccountInfo);
                message = "Amount added successfully in the account number: " + bankAccountInfo.getAccountNumber() + ".";
            } else
                message = "Sorry, you cannot add money to your business account.";
        }
        return message;
    }
}