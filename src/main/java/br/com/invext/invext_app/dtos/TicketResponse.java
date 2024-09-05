package br.com.invext.invext_app.dtos;


import br.com.invext.invext_app.enums.TicketStatus;
import br.com.invext.invext_app.enums.TicketType;

import java.time.LocalDateTime;

public class TicketResponse {

    private String id;
    private String description;
    private TicketType ticketType;
    private TicketStatus ticketStatus;
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
