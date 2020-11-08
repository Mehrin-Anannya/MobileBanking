package com.progotisystemsltd.assigment.mobilebanking.service;

import com.progotisystemsltd.assigment.mobilebanking.model.*;
import com.progotisystemsltd.assigment.mobilebanking.repository.AccountTypeRepository;
import com.progotisystemsltd.assigment.mobilebanking.repository.BankAccountRepository;
import com.progotisystemsltd.assigment.mobilebanking.repository.BusinessAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private BusinessAccountRepository businessAccountRepository;

    //Bank Account Creation
    @Override
    public String createBankAccount(BankAccountInfo bankAccountInfo, Model model) {
        String message ="";
        BankAccount bankAccount;
        AccountType accountType;
        //Check whether there is already an account with the given mobile phone number
        bankAccount = bankAccountRepository.findBankAccountByMobilePhoneNumber(bankAccountInfo.getBankAccount().getMobilePhoneNumber());
        //Create New Bank Account
        if (bankAccount == null) {
            //Find Account Type Name from the user input
            accountType = accountTypeRepository.findById(bankAccountInfo.getBankAccount().getAccountType().getAccountTypeId()).orElse(null);
            bankAccountInfo.getBankAccount().setAccountNumber(getAccountNumber(accountType));
            //Create New Bank Account
            bankAccount = bankAccountRepository.save(bankAccountInfo.getBankAccount());
            //Create Business Account
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
    //Account Number generation
    public String getAccountNumber(AccountType accountType){
        BankAccount bankAccount = bankAccountRepository.findTopByAccountType_AccountTypeId(accountType.getAccountTypeId());
        String accountNumber = bankAccount.getAccountNumber();
        String delimiters = "-";
        // analyzing the string
        String[] tokensVal = accountNumber.split(delimiters);
        tokensVal[1] = "-0" + String.valueOf(Integer.parseInt(tokensVal[1]) + 1);
        return tokensVal[0] + tokensVal[1];
    }

    // Get all bank account information
    @Override
    public List<BankAccount> getAllBankAccount() {
        return bankAccountRepository.findAll();
    }

    //Transfer Money
    @Override
    @Transactional
    public String getTransferMoney(TransferMoney transferMoney) {
        String transferMoneyMessage = "";
        //Get sender's bank account information
        BankAccount senderBankAccount = bankAccountRepository.findBankAccountByAccountNumber(transferMoney.getSenderBankAccount().getAccountNumber().trim());
        //Check sender's bank account existence
        if(senderBankAccount == null){
            transferMoneyMessage = "Sorry, sender's bank account doesn't exist.";
        }else if (senderBankAccount.getAccountType().getAccountTypeName().equals(AccountTypeNames.PERSONAL.name())) {
                BankAccount receiverBankAccount = bankAccountRepository.findBankAccountByAccountNumber(transferMoney.getReceiverBankAccount().getAccountNumber().trim());
                //Check receiver's bank account existence
                if (receiverBankAccount == null) {
                    transferMoneyMessage = "Sorry, receiver's bank account doesn't exist.";
                } else if (senderBankAccount.getBalance() == null)
                        transferMoneyMessage = "Sorry, your balance is zero.";
                    //Money can be sent from sender's bank account by keeping the minimum balance 500
                        else if ((senderBankAccount.getBalance() - transferMoney.getTransferAmount()) > 500) {
                            //transferring money
                            senderBankAccount.setBalance(senderBankAccount.getBalance() - transferMoney.getTransferAmount());
                            receiverBankAccount.setBalance(receiverBankAccount.getBalance() + transferMoney.getTransferAmount());
                            bankAccountRepository.save(senderBankAccount);
                            bankAccountRepository.save(receiverBankAccount);
                            transferMoneyMessage = "Amount transferred successfully.";
                        } else {
                            transferMoneyMessage = "Sorry, money cannot be transferred from your account. It exceeds your minimum balance.";
                        }
            }else
                transferMoneyMessage = "Sorry, money cannot be transferred from Business account.";
        return transferMoneyMessage;
    }

    //Check Balance
    @Override
    public BankAccount checkBalanceWithMPN(String mobilePhoneNumber) {
        return bankAccountRepository.findBankAccountByMobilePhoneNumber(mobilePhoneNumber);
    }

    //Add Money
    @Override
    public String updateBankAccount(BankAccount bankAccount) {
        String message;
        BankAccount bankAccountInfo;
        //Check whether there is an account with the given mobile phone number
        bankAccountInfo = bankAccountRepository.findBankAccountByMobilePhoneNumber(bankAccount.getMobilePhoneNumber());
        if (bankAccountInfo == null) {
            message = "The account doesn't exist. ";
        } else {
            //Add money to the personal account
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

    //withdraw Money
    @Override
    public String getWithdrawMoney(BankAccount drawMoney) {
        String withdrawMoneyMessage = "";
        BankAccount bankAccount;
        bankAccount = bankAccountRepository.findBankAccountByAccountNumber(drawMoney.getAccountNumber());
        if (bankAccount == null) {
            withdrawMoneyMessage = "The account doesn't exist. ";
        } else if (bankAccount.getBalance() == 0.0) {
            withdrawMoneyMessage = "Money cannot be withdrawn because your current balance is 0/.";
        }//There should be minimum balance 0 to withdraw money
        //For withdraw amount less than or equal to 5000 with 1.5% charge calculation
        else if (drawMoney.getBalance()<=5000 && (bankAccount.getBalance() - drawMoney.getBalance()-((drawMoney.getBalance() * 1.5) / 100)) >= 0) {
            bankAccount.setBalance(bankAccount.getBalance() - drawMoney.getBalance() - ((drawMoney.getBalance() * 1.5) / 100));
            bankAccountRepository.save(bankAccount);
            withdrawMoneyMessage = "Money withdrawn successfully";
        }else if(drawMoney.getBalance() > 5000 && (bankAccount.getBalance() - drawMoney.getBalance() - 100) >= 0) {
            //For withdraw amount greater than 5000 with 100 tk charge calculation
            bankAccount.setBalance(bankAccount.getBalance() - drawMoney.getBalance() - 100);
            bankAccountRepository.save(bankAccount);
            withdrawMoneyMessage = "Money withdrawn successfully";
            } //To prevent minimum balance from being negative
            else{
            withdrawMoneyMessage = "Sorry, money cannot be withdrawn. It exceeds your minimum balance Tk. 0.";
            }
        return withdrawMoneyMessage;
    }
}