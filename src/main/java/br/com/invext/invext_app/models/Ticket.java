package br.com.invext.invext_app.models;

import br.com.invext.invext_app.enums.TicketStatus;
import br.com.invext.invext_app.enums.TicketType;

import java.time.LocalDateTime;

public class Ticket {

    private String id;
    private String description;
    private TicketType ticketType;
    private TicketStatus ticketStatus;
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public Ticket setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Ticket setDescription(String description) {
        this.description = description;
        return this;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public Ticket setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
        return this;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public Ticket setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Ticket setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", ticketType=" + ticketType +
                ", ticketStatus=" + ticketStatus +
                ", createdAt=" + createdAt +
                '}';
    }
}
