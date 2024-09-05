package br.com.invext.invext_app.dtos;


import br.com.invext.invext_app.enums.TicketStatus;
import br.com.invext.invext_app.enums.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class TicketResponse {

    @Schema(description = "Ticket ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "63e42e3f-8ef3-4485-ab38-80a2e31720a4")
    private String id;

    @Schema(description = "Ticket description", requiredMode = Schema.RequiredMode.REQUIRED, example = "My card is blocked")
    private String description;

    @Schema(description = "Ticket type", requiredMode = Schema.RequiredMode.REQUIRED, enumAsRef = true, example = "CARDS")
    private TicketType ticketType;

    @Schema(description = "Ticket status", requiredMode = Schema.RequiredMode.REQUIRED, enumAsRef = true, example = "IN_PROGRESS")
    private TicketStatus ticketStatus;

    @Schema(description = "Date when the ticket was created", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "TicketResponse{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", ticketType=" + ticketType +
                ", ticketStatus=" + ticketStatus +
                ", createdAt=" + createdAt +
                '}';
    }
}
