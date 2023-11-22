package com.borawski.jobtaskone.view;

import com.borawski.jobtaskone.api.TransferRepository;
import com.borawski.jobtaskone.transfers.TransferCollection;
import com.borawski.jobtaskone.transfers.TransferCollectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ViewService {
    private TransferRepository transferRepository;

    public ViewService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Mono<Page<TransferCollectionDTO>> findAllTransfers(final Pageable pageable) {
        final int pageSize = pageable.getPageSize();
        final int currentPage = pageable.getPageNumber();

        return this.transferRepository.findAllBy(pageable).flatMap(this::mapToDTO).collectList()
                .zipWith(this.transferRepository.count())
                .map(i -> new PageImpl<>(i.getT1(), PageRequest.of(currentPage, pageSize), i.getT2()));
    }

    private Mono<TransferCollectionDTO> mapToDTO(final TransferCollection transferCollection) {
        final TransferCollectionDTO transferCollectionDTO = new TransferCollectionDTO();
        transferCollectionDTO.setSenderAccountNumber(transferCollection.getSenderAccountNumber());
        transferCollectionDTO.setDescription(transferCollection.getDescription());
        transferCollectionDTO.setTransactionDate(transferCollection.getTransactionDate());
        transferCollectionDTO.setAccountingDate(transferCollection.getAccountingDate());
        transferCollectionDTO.setTransactionType(transferCollection.getTransactionType());
        transferCollectionDTO.setRecieverAccountNumber(transferCollection.getRecieverAccountNumber());
        transferCollectionDTO.setRecieverName(transferCollection.getRecieverName());
        transferCollectionDTO.setDescription(transferCollection.getDescription());
        transferCollectionDTO.setAmount(transferCollection.getAmount());
        transferCollectionDTO.setTotal(transferCollection.getTotal());
        transferCollectionDTO.setCurrency(transferCollection.getCurrency());
        transferCollectionDTO.setCreatedAt(transferCollection.getCreatedAt());
        return Mono.just(transferCollectionDTO);
    }

}
