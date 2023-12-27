package com.borawski.jobtaskone.analysis;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface AnalysisRepository extends ReactiveMongoRepository<AnalysisCollection, String> {
    Mono<AnalysisCollection> findByCreatedAt(LocalDateTime creationTime);

}