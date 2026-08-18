package com.oksys.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketRequest {

    private String eventName;
    private String ticketCode;
    private BigDecimal price;
    private String status;
    private String stadiumName;
    private String standName;   // Diubah dari stand_name ke camelCase
    private String seatNumber;  // Diubah dari BigDecimal ke String agar sama dengan Entity
    private String gate;
    private LocalDateTime matchDate; // Diubah dari String ke LocalDateTime

    // Getter & Setter
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
}