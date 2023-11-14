package com.borawski.jobtaskone.api;

import com.borawski.jobtaskone.transfers.TransferCollection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RestApiControllers {

    TransferRepository transferRepository;
    RestApiService restApiService;
    public RestApiControllers(TransferRepository transferRepository, RestApiService restApiService) {
        this.transferRepository = transferRepository;
        this.restApiService = restApiService;
    }


    @GetMapping("/transfers")
    public Flux<TransferCollection> getAllTransfers() {
        return transferRepository.findAll()
                .switchIfEmpty(Flux.error(new TransferNotFoundException()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class TransferNotFoundException extends RuntimeException {
    }

    @PostMapping("/transfers")
    public Mono<ResponseEntity<List<TransferCollection>>> uploadNewTransfers(@RequestPart("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                Document document = Jsoup.parse(new String(file.getBytes(), "UTF-8"));
                Element table = document.select("table").first();

                if (table != null) {
                    Elements rows = table.select("tr:gt(0)");
                    List<TransferCollection> transfers = rows.stream()
                            .map(restApiService::convertRowToTransfer)
                            .collect(Collectors.toList());

                    return Flux.fromIterable(transfers)
                            .flatMap(restApiService::saveTransferIfNotExists)
                            .collectList()
                            .map(savedTransfers -> new ResponseEntity<>(savedTransfers, HttpStatus.OK));
                } else {
                    return Mono.just(new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT));
                }

            } catch (Exception e) {
                return Mono.just(new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST));
            }
        }
        return Mono.just(new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT));
    }




}
