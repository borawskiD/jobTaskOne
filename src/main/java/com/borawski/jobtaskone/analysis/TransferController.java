package com.borawski.jobtaskone.analysis;

import com.borawski.jobtaskone.api.TransferRepository;
import com.borawski.jobtaskone.transfers.TransferCollection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;
@Controller
public class TransferController {
    TransferRepository transferRepository;
    AnalysisRepository analysisRepository;

    public TransferController(TransferRepository transferRepository, AnalysisRepository analysisRepository) {
        this.transferRepository = transferRepository;
        this.analysisRepository = analysisRepository;
    }

    @GetMapping("/findTransfers/{name}")
    @ResponseBody
    public String getAllTransfers(@PathVariable String name) {
        Flux<String> transfers =  transferRepository.findAll().filter((transfer) -> Objects.equals(transfer.getRecieverName(), name)).map(TransferCollection::getSenderAccountNumber);
        System.out.println("Test:");
        LocalDateTime creationTime = LocalDateTime.now();
        analysisRepository.save(new AnalysisCollection(name, new LinkedList<>(), new LinkedList<>(), "New", creationTime))
                .flatMap(savedAnalysis -> analysisRepository.findByCreatedAt(creationTime))
                .flatMap(foundAnalysis -> transfers.collectList()
                        .map(transferList -> {
                            foundAnalysis.getCommonUsers().addAll(transferList);
                            return foundAnalysis;
                        }))
                .flatMap(updatedAnalysis -> analysisRepository.save(updatedAnalysis))
                .subscribe();


        return "test";

    }

}
