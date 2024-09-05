package br.com.invext.invext_app.controllers;

import br.com.invext.invext_app.dtos.TicketRequest;
import br.com.invext.invext_app.dtos.TicketResponse;
import br.com.invext.invext_app.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketResponse> create(@RequestBody @Valid TicketRequest ticketRequest) {
        return ResponseEntity.ok().body(ticketService.create(ticketRequest));
    }
}
