package com.borawski.jobtaskone;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "transfers")
public class TransferEntity {
    @Id
    private String id;
    private String senderAccountNumber;
    private LocalDate transactionDate;
    private LocalDate accountingDate;
    private String recieverAccountNumber;
    private String recieverName;
    private String description;
    private String amount;
    private String currency;

    private LocalDateTime createdAt;

    public TransferEntity() {
    }

    public TransferEntity(String senderAccountNumber, LocalDate transactionDate, LocalDate accountingDate, String recieverAccountNumber, String recieverName, String description, String amount, String currency, LocalDateTime createdAt) {
        this.senderAccountNumber = senderAccountNumber;
        this.transactionDate = transactionDate;
        this.accountingDate = accountingDate;
        this.recieverAccountNumber = recieverAccountNumber;
        this.recieverName = recieverName;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getRecieverAccountNumber() {
        return recieverAccountNumber;
    }

    public void setRecieverAccountNumber(String recieverAccountNumber) {
        this.recieverAccountNumber = recieverAccountNumber;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
