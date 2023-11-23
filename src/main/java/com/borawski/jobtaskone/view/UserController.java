package com.borawski.jobtaskone.view;

import com.borawski.jobtaskone.api.RestApiService;
import com.borawski.jobtaskone.api.TransferRepository;
import com.borawski.jobtaskone.transfers.TransferCollection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    TransferRepository transferRepository;
    ViewService viewService;
    RestApiService restApiService;

    public UserController(TransferRepository transferRepository, ViewService viewService, RestApiService restApiService) {
        this.transferRepository = transferRepository;
        this.viewService = viewService;
        this.restApiService = restApiService;
    }

    @GetMapping("/view")
    public Mono<Rendering> list(@PageableDefault(size = 10) final Pageable pageable,
                                final Model  model, final WebSession session) {

        return setRedirectAttributes(model, session).thenReturn(Rendering.view("transferView")
                .modelAttribute("transfers", viewService.findAllTransfers(pageable)).build());

    }

    @PostMapping("/upload")
    public RedirectView uploadNewTransfers(@RequestPart("file") FilePart filePart) {
        filePart.content()
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
                })
                .subscribe();

        return new RedirectView("view");
    }



    private Mono<Void> setRedirectAttributes(final Model model, final WebSession session) {
        return Mono.fromRunnable(
                () -> {
                    if(session.getAttribute("MSG_SUCCESS") != null) {
                        model.addAttribute("MSG_SUCCESS", session.getAttribute("MSG_SUCCESS"));
                        session.getAttributes().remove("MSG_SUCCESS");
                    }
                });
    }

}
