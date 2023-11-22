package com.borawski.jobtaskone.view;

import com.borawski.jobtaskone.api.TransferRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.web.server.WebSession;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

@Controller
public class UserController {
    TransferRepository transferRepository;
    ViewService viewService;

    public UserController(TransferRepository transferRepository, ViewService viewService) {
        this.transferRepository = transferRepository;
        this.viewService = viewService;
    }

    @GetMapping("/view")
    public Mono<Rendering> list(@PageableDefault(size = 10) final Pageable pageable,
                                final Model  model, final WebSession session) {

        return setRedirectAttributes(model, session).thenReturn(Rendering.view("transferView")
                .modelAttribute("transfers", viewService.findAllTransfers(pageable)).build());

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
