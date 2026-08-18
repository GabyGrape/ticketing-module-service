package com.oksys.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets", indexes = {
        @Index(name = "idx_ticket_status", columnList = "status"),
        @Index(name = "idx_ticket_event", columnList = "eventName")
})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @Column(name = "ticket_code", nullable = false, unique = true)
    private String ticketCode;

    @Column(nullable = true)
    private BigDecimal price;

    @Column(nullable = true)
    private String status;

    @Column(name = "stadium_name", nullable = false)
    private String stadiumName;

    @Column(name = "stand_name", nullable = false)
    private String standName;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "gate")
    private String gate;

    @Column(name = "match_date")
    private LocalDateTime matchDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Default Constructor (Wajib JPA)
    public Ticket() {}

    // Parameterized Constructor Lengkap (Diperbarui untuk Fitur Stadion)
    public Ticket(String eventName, String ticketCode, BigDecimal price, String status,
                  String stadiumName, String standName, String seatNumber, String gate, LocalDateTime matchDate) {
        this.eventName = eventName;
        this.ticketCode = ticketCode;
        this.price = price;
        this.status = status;
        this.stadiumName = stadiumName;
        this.standName = standName;
        this.seatNumber = seatNumber;
        this.gate = gate;
        this.matchDate = matchDate;
    }

    // Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getTicketCode() { return ticketCode; }
    public void setTicketCode(String ticketCode) { this.ticketCode = ticketCode; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getStadiumName() { return stadiumName; }
    public void setStadiumName(String stadiumName) { this.stadiumName = stadiumName; }

    public String getStandName() { return standName; }
    public void setStandName(String standName) { this.standName = standName; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getGate() { return gate; }
    public void setGate(String gate) { this.gate = gate; }

    public LocalDateTime getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDateTime matchDate) { this.matchDate = matchDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}