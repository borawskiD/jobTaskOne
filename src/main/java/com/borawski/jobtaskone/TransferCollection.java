package com.borawski.jobtaskone;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "transfers")
public class TransferCollection {
    @Id
    private String id;
    private String senderAccountNumber;
    private LocalDate transactionDate;
    private LocalDate accountingDate;
    private String transactionType;
    private String recieverAccountNumber;
    private String recieverName;
    private String description;
    private Double amount;
    private Double total;
    private String currency;

    private LocalDateTime createdAt;

    public TransferCollection() {
    }


    public TransferCollection(String senderAccountNumber, LocalDate transactionDate, LocalDate accountingDate, String transactionType, String recieverAccountNumber, String recieverName, String description, Double amount, Double total, String currency, LocalDateTime createdAt) {
        this.senderAccountNumber = senderAccountNumber;
        this.transactionDate = transactionDate;
        this.accountingDate = accountingDate;
        this.transactionType = transactionType;
        this.recieverAccountNumber = recieverAccountNumber;
        this.recieverName = recieverName;
        this.description = description;
        this.amount = amount;
        this.total = total;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
