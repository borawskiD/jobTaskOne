package com.borawski.jobtaskone;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends ReactiveMongoRepository<TransferCollection, String> {
}
