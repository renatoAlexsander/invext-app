package br.com.invext.invext_app.controllers;

import br.com.invext.invext_app.dtos.TicketRequest;
import br.com.invext.invext_app.dtos.TicketResponse;
import br.com.invext.invext_app.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Create new ticket", description = "Create new ticket and send to the respective team for handling.")
    @ApiResponse(responseCode = "201", description = "Ticket created with success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponse.class)) })
    public ResponseEntity<TicketResponse> create(@RequestBody @Valid TicketRequest ticketRequest) {
        return ResponseEntity.ok().body(ticketService.create(ticketRequest));
    }
}
