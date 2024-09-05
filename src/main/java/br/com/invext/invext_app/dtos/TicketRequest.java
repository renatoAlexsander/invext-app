package br.com.invext.invext_app.dtos;


import br.com.invext.invext_app.enums.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TicketRequest {

    @NotEmpty(message = "description is required")
    @Schema(description = "description of your problem", requiredMode = Schema.RequiredMode.REQUIRED, example = "My card is blocked")
    private String description;

    @NotNull(message = "ticket type is required")
    @Schema(description = "Ticket type", enumAsRef = true, requiredMode = Schema.RequiredMode.REQUIRED, example = "CARDS")
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
