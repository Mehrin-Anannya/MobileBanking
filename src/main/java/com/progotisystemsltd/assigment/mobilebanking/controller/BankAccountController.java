package com.progotisystemsltd.assigment.mobilebanking.controller;

import com.progotisystemsltd.assigment.mobilebanking.model.BankAccount;
import com.progotisystemsltd.assigment.mobilebanking.model.BankAccountInfo;
import com.progotisystemsltd.assigment.mobilebanking.model.TransferMoney;
import com.progotisystemsltd.assigment.mobilebanking.service.BankAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankAccountController {

    @Autowired
    private BankAccountServiceImpl bankAccountService;

    @GetMapping("/")
    public String viewIndexPage(Model model) {
        return "index";
    }

    @GetMapping("/viewAllBankAccounts")
    public String getAllBankAccountInfo(Model model) {
        model.addAttribute("viewAllBankAccounts", bankAccountService.getAllBankAccount());
        return "viewaccounts";
    }

    //set bank account as a model attribute to pre-populate the form
    @GetMapping(value = "/createAnAccountPage")
    public String showCreateAnAccountPage(Model model){
        model.addAttribute("banKAccountInfo", new BankAccountInfo());
        return "openanaccount";
    }

    @PostMapping(value = "/createAnAccount")
    public String createAnAccount(@ModelAttribute("banKAccountInfo") BankAccountInfo bankAccountInfo, Model model){
        String message;
        message = bankAccountService.createBankAccount(bankAccountInfo, model);
        model.addAttribute("message", message);
        model.addAttribute("banKAccountInfo", new BankAccountInfo());
        return "openanaccount";
    }

    @GetMapping(value = "/addMoneyPage")
    public String showAddMoneyPage(Model model){
        BankAccount bankAccount = new BankAccount();
        model.addAttribute("bankAccount", bankAccount);
        return "addmoney";
    }

    @PostMapping(value = "/addMoney")
    public String addMoneyToBankAccount(@ModelAttribute("bankAccount")BankAccount bankAccount, Model model){
        String message;
        message = bankAccountService.updateBankAccount(bankAccount);
        //When bank account doesn't exists
        model.addAttribute("bankAccount", new BankAccount());
        model.addAttribute("addMoneyMessage", message);
        return "addmoney";
    }

    @GetMapping(value = "/checkBalancePage")
    public String showCheckBalancePage(Model model){
        model.addAttribute("bankAccount", new BankAccount());
        return "checkbalance";
    }

    @PostMapping(value = "/checkBalance")
    public String getCheckBalanceByMPN(@ModelAttribute("bankAccount") BankAccount bankAccount, Model model){
        String checkBalanceMessage = "";
        bankAccount = bankAccountService.checkBalanceWithMPN(bankAccount.getMobilePhoneNumber());
        //When there is no bank account with this mobile number
        if(bankAccount == null){
            checkBalanceMessage = "Sorry, account not available with this number";
            model.addAttribute("checkBalanceMessage",  checkBalanceMessage);
            model.addAttribute("bankAccount",  new BankAccount());
        }
        model.addAttribute("bankAccount", bankAccount);
        return "checkbalance";
    }

    @GetMapping(value = "/transferMoneyPage")
    public String showTransferMoneyPage(Model model){
        model.addAttribute("transferMoney", new TransferMoney());
        return "transfermoney";
    }

    @PostMapping(value = "/transferMoney")
    public String transferMoney(@ModelAttribute("transferMoney") TransferMoney transferMoney, Model model){
        String transferMoneyMessage ="";
        transferMoneyMessage = bankAccountService.getTransferMoney(transferMoney);
        model.addAttribute("transferMoneyMessage", transferMoneyMessage);
        model.addAttribute("transferMoney", new TransferMoney());
        return "transfermoney";
    }

    @GetMapping(value = "/withdrawMoneyPage")
    public String showWithdrawMoneyPage(Model model){
        model.addAttribute("bankAccount", new BankAccount());
        return "withdrawmoney";
    }

    @PostMapping(value = "/withdrawMoney")
    public String withdrawMoney(@ModelAttribute("bankAccount") BankAccount bankAccount, Model model){
        String withdrawMoneyMessage ="";
        withdrawMoneyMessage = bankAccountService.getWithdrawMoney(bankAccount);
        model.addAttribute("withdrawMoneyMessage", withdrawMoneyMessage);
        model.addAttribute("bankAccount", new BankAccount());
        return "withdrawmoney";
    }
}
