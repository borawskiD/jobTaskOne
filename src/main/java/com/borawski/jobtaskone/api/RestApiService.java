package com.borawski.jobtaskone.api;

import com.borawski.jobtaskone.transfers.TransferCollection;
import com.borawski.jobtaskone.transfers.TransferCollectionDTO;
import com.borawski.jobtaskone.transfers.TransferWrapper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RestApiService {
    TransferRepository transferRepository;

    public RestApiService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Mono<TransferCollection> saveTransferIfNotExists(TransferCollection transfer) {
        return transferRepository.findByTransactionDateAndSenderAccountNumber(
                        transfer.getTransactionDate(), transfer.getDescription())
                .switchIfEmpty(Mono.defer(() -> transferRepository.save(transfer)));
    }

    public TransferCollection convertRowToTransfer(Element row) {
        Elements cells = row.select("td, th");
        Element[] elementsArray = cells.toArray(new Element[0]);
        TransferCollectionDTO exampleTransferEntity = TransferWrapper.convertElement(elementsArray);
        return TransferWrapper.convertToEntity(exampleTransferEntity);
    }

}
