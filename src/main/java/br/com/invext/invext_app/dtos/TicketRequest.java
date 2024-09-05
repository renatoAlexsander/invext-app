package br.com.invext.invext_app.dtos;


import br.com.invext.invext_app.enums.TicketType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TicketRequest {

    @NotEmpty(message = "description is required")
    private String description;

    @NotNull(message = "ticket type is required")
    private TicketType ticketType;

    public @NotEmpty String getDescription() {
        return description;
    }

    public TicketRequest setDescription(@NotEmpty String description) {
        this.description = description;
        return this;
    }

    public @NotNull TicketType getTicketType() {
        return ticketType;
    }

    public TicketRequest setTicketType(@NotNull TicketType ticketType) {
        this.ticketType = ticketType;
        return this;
    }

    @Override
    public String toString() {
        return "TicketRequest{" +
                "description='" + description + '\'' +
                ", ticketType=" + ticketType +
                '}';
    }
}
