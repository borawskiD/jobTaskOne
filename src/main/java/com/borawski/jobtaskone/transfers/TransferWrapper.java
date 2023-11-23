package com.borawski.jobtaskone.transfers;

import org.jsoup.nodes.Element;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransferWrapper {
    public static TransferCollectionDTO convertElement(Element[] elements) {
        TransferCollectionDTO transferEntityDTO = new TransferCollectionDTO();
        transferEntityDTO.setSenderAccountNumber(elements[0].text());
        transferEntityDTO.setTransactionDate(LocalDate.parse(elements[1].text()));
        transferEntityDTO.setAccountingDate(LocalDate.parse(elements[2].text()));
        transferEntityDTO.setTransactionType(elements[3].text());
        String verifiedAccountNumber = parseAccountNumber(elements[4].text());
        transferEntityDTO.setRecieverAccountNumber(verifiedAccountNumber);
        transferEntityDTO.setRecieverName(elements[5].text());
        transferEntityDTO.setDescription(elements[6].text());
        if (elements[7].text().length() != 0) transferEntityDTO.setAmount(Double.parseDouble(elements[7].text()));
        if (elements[8].text().length() != 0) transferEntityDTO.setAmount(Double.parseDouble(elements[8].text()));
        transferEntityDTO.setTotal(Double.parseDouble(elements[9].text()));
        transferEntityDTO.setCurrency(elements[10].text());
        transferEntityDTO.setCreatedAt(LocalDateTime.now());
        return transferEntityDTO;
    }

    private static String parseAccountNumber(String input){
        if (input == null) return "";
        String regexPattern = "\\d+|\\s+";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(input);
        StringBuilder result = new StringBuilder();
        if (input.length() > 0) result.append("PL");
        while (matcher.find()) {
            result.append(matcher.group());
        }
        return result.toString();
    }

    public static TransferCollection convertToEntity(TransferCollectionDTO transferEntityDTO) {
        TransferCollection transferEntity = new TransferCollection();
        transferEntity.setSenderAccountNumber(transferEntityDTO.getSenderAccountNumber());
        transferEntity.setTransactionDate(transferEntityDTO.getTransactionDate());
        transferEntity.setAccountingDate(transferEntityDTO.getAccountingDate());
        transferEntity.setRecieverAccountNumber(transferEntityDTO.getRecieverAccountNumber());
        transferEntity.setTransactionType(transferEntityDTO.getTransactionType());
        transferEntity.setRecieverName(transferEntityDTO.getRecieverName());
        transferEntity.setDescription(transferEntityDTO.getDescription());
        transferEntity.setAmount(transferEntityDTO.getAmount());
        transferEntity.setTotal(transferEntityDTO.getTotal());
        transferEntity.setCurrency(transferEntityDTO.getCurrency());
        transferEntity.setCreatedAt(transferEntityDTO.getCreatedAt());

        return transferEntity;
    }
}
