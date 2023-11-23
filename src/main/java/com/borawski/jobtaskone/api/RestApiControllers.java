package com.borawski.jobtaskone.api;

import com.borawski.jobtaskone.transfers.TransferCollection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
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


    @EventListener(ApplicationReadyEvent.class)
    public void insertStartData(){
        transferRepository.deleteAll().subscribe();
    }


    @PostMapping("/transfers")
    public Flux<TransferCollection> uploadNewTransfers(@RequestPart("file") FilePart filePart) {
        return filePart.content()
                .flatMap(dataBuffer -> {
                    String content = dataBuffer.toString(StandardCharsets.UTF_8);

                    try {
                        Document document = Jsoup.parse(content);
                        Element table = document.select("table").first();

                        if (table != null) {
                            Elements rows = table.select("tr:gt(0)");
                            List<TransferCollection> transfers = rows.stream()
                                    .map(restApiService::convertRowToTransfer)
                                    .collect(Collectors.toList());

                            return Flux.fromIterable(transfers)
                                    .flatMap(restApiService::saveTransferIfNotExists);
                        } else {
                            return Flux.empty();
                        }

                    } catch (Exception e) {
                        return Flux.empty();
                    }
                });
    }

}
