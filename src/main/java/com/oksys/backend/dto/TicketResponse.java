package com.oksys.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketResponse {
    private Long id;
    private String eventName;
    private String ticketCode;
    private BigDecimal price;
    private String status;
    private String stadiumName;
    private String standName;
    private String seatNumber; // Diubah ke String agar konsisten
    private String gate;
    private LocalDateTime matchDate;
    private LocalDateTime createdAt;

    // Constructor dengan urutan parameter yang disesuaikan dengan TicketService
    public TicketResponse(Long id, String eventName, String ticketCode, BigDecimal price, String status,
                          String stadiumName, String standName, String seatNumber, String gate,
                          LocalDateTime matchDate, LocalDateTime createdAt) {
        this.id = id;
        this.eventName = eventName;
        this.ticketCode = ticketCode;
        this.price = price;
        this.status = status;
        this.stadiumName = stadiumName;
        this.standName = standName;
        this.seatNumber = seatNumber;
        this.gate = gate;
        this.matchDate = matchDate;
        this.createdAt = createdAt;
    }

    // Getter
    public Long getId() { return id; }
    public String getEventName() { return eventName; }
    public String getTicketCode() { return ticketCode; }
    public BigDecimal getPrice() { return price; }
    public String getStatus() { return status; }
    public String getStadiumName() { return stadiumName; }
    public String getStandName() { return standName; }
    public String getSeatNumber() { return seatNumber; }
    public String getGate() { return gate; }
    public LocalDateTime getMatchDate() { return matchDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}