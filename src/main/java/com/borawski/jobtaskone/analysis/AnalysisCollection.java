package com.borawski.jobtaskone.analysis;

import com.borawski.jobtaskone.transfers.TransferCollection;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Document(collection = "analysis")
public class AnalysisCollection {
    @Id
    private String id;
    private String input;
    private LinkedList<String> commonUsers;
    private LinkedList<TransferCollection> transfers;
    private String status;
    private LocalDateTime createdAt;

    public AnalysisCollection() {
    }

    public AnalysisCollection(String input, LinkedList<String> commonUsers, LinkedList<TransferCollection> transfers, String status, LocalDateTime createdAt) {
        this.input = input;
        this.commonUsers = commonUsers;
        this.transfers = transfers;
        this.status = status;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public LinkedList<String> getCommonUsers() {
        return commonUsers;
    }

    public void setCommonUsers(LinkedList<String> commonUsers) {
        this.commonUsers = commonUsers;
    }

    public LinkedList<TransferCollection> getTransfers() {
        return transfers;
    }

    public void setTransfers(LinkedList<TransferCollection> transfers) {
        this.transfers = transfers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
