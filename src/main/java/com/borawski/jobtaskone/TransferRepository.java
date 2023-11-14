package com.borawski.jobtaskone;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransferRepository extends MongoRepository<TransferCollection, String> {
}
