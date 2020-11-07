package com.progotisystemsltd.assigment.mobilebanking.model;

public class TransferMoney {
    private BankAccount senderBankAccount;
    private BankAccount receiverBankAccount;
    private Double transferAmount;

    public BankAccount getSenderBankAccount() {
        return senderBankAccount;
    }

    public void setSenderBankAccount(BankAccount senderBankAccount) {
        this.senderBankAccount = senderBankAccount;
    }

    public BankAccount getReceiverBankAccount() {
        return receiverBankAccount;
    }

    public void setReceiverBankAccount(BankAccount receiverBankAccount) {
        this.receiverBankAccount = receiverBankAccount;
    }

    public Double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Double transferAmount) {
        this.transferAmount = transferAmount;
    }
}
