package com.borawski.jobtaskone.api;

import com.borawski.jobtaskone.transfers.TransferCollection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface TransferRepository extends ReactiveMongoRepository<TransferCollection, String> {
    Mono<TransferCollection> findByTransactionDateAndSenderAccountNumber(LocalDate transactionDate, String senderAccountNumber);
    Flux<TransferCollection> findAllBy(Pageable pageable);

}
