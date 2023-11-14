package com.borawski.jobtaskone.transfers;

import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransferWrapper {
    public static TransferCollectionDTO convertElement(Element[] elements) {
        TransferCollectionDTO transferEntityDTO = new TransferCollectionDTO();
        transferEntityDTO.setSenderAccountNumber(elements[0].text());
        transferEntityDTO.setTransactionDate(LocalDate.parse(elements[1].text()));
        transferEntityDTO.setAccountingDate(LocalDate.parse(elements[2].text()));
        transferEntityDTO.setTransactionType(elements[3].text());
        transferEntityDTO.setRecieverAccountNumber(elements[4].text());
        transferEntityDTO.setRecieverName(elements[5].text());
        transferEntityDTO.setDescription(elements[6].text());
        if (elements[7].text().length() != 0) transferEntityDTO.setAmount(Double.parseDouble(elements[7].text()));
        if (elements[8].text().length() != 0) transferEntityDTO.setAmount(Double.parseDouble(elements[8].text()));
        transferEntityDTO.setTotal(Double.parseDouble(elements[9].text()));
        transferEntityDTO.setCurrency(elements[10].text());
        transferEntityDTO.setCreatedAt(LocalDateTime.now());
        return transferEntityDTO;
    }

    public static TransferCollection convertToEntity(TransferCollectionDTO transferEntityDTO) {
        TransferCollection transferEntity = new TransferCollection();
        transferEntity.setSenderAccountNumber(transferEntityDTO.getSenderAccountNumber());
        transferEntity.setTransactionDate(transferEntityDTO.getTransactionDate());
        transferEntity.setAccountingDate(transferEntityDTO.getAccountingDate());
        transferEntity.setRecieverAccountNumber(transferEntityDTO.getRecieverAccountNumber());
        transferEntity.setRecieverName(transferEntityDTO.getRecieverName());
        transferEntity.setDescription(transferEntityDTO.getDescription());
        transferEntity.setAmount(transferEntityDTO.getAmount());
        transferEntity.setTotal(transferEntityDTO.getTotal());
        transferEntity.setCurrency(transferEntityDTO.getCurrency());
        transferEntity.setCreatedAt(transferEntityDTO.getCreatedAt());
        return transferEntity;
    }
}
