package com.oksys.backend.service.impl;

import com.oksys.backend.dto.TicketRequest;
import com.oksys.backend.dto.TicketResponse;
import com.oksys.backend.exception.ResourceNotFoundException;
import com.oksys.backend.model.Ticket;
import com.oksys.backend.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // CREATE TICKET
    @Transactional
    public TicketResponse createTicket(TicketRequest request) {
        // 1. Mengubah DTO Request menjadi Entity menggunakan Constructor Lengkap yang baru
        Ticket ticket = new Ticket(
                request.getEventName(),
                request.getTicketCode(),
                request.getPrice(),
                request.getStatus(),
                request.getStadiumName(),
                request.getStandName(),
                request.getSeatNumber(),
                request.getGate(),
                request.getMatchDate()
        );

        // 2. Simpan ke Database
        Ticket savedTicket = ticketRepository.save(ticket);

        // 3. Mapping ke Response
        return mapToResponse(savedTicket);
    }

    // READ ALL TICKETS (PAGINATION)
    @Transactional(readOnly = true)
    public Page<TicketResponse> getAllTickets(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return ticketRepository.findAll(pageable).map(this::mapToResponse);
    }

    // READ TICKET BY ID
    @Transactional(readOnly = true)
    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tiket dengan ID " + id + " tidak ditemukan"));
        return mapToResponse(ticket);
    }

    // UPDATE TICKET
    @Transactional
    public TicketResponse updateTicket(Long id, TicketRequest request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tiket tidak ditemukan"));

        // Perbarui field lama
        ticket.setEventName(request.getEventName());
        ticket.setTicketCode(request.getTicketCode());
        ticket.setPrice(request.getPrice());
        ticket.setStatus(request.getStatus());

        // Perbarui field-field baru (Stadion)
        ticket.setStadiumName(request.getStadiumName());
        ticket.setStandName(request.getStandName());
        ticket.setSeatNumber(request.getSeatNumber());
        ticket.setGate(request.getGate());
        ticket.setMatchDate(request.getMatchDate());

        Ticket updatedTicket = ticketRepository.save(ticket);
        return mapToResponse(updatedTicket);
    }

    // DELETE TICKET
    @Transactional
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tiket tidak ditemukan");
        }
        ticketRepository.deleteById(id);
    }

    // HELPER MAPPER: Mengonversi Entity ke DTO Response (Lengkap dengan data Stadion)
    private TicketResponse mapToResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getEventName(),
                ticket.getTicketCode(),
                ticket.getPrice(),
                ticket.getStatus(),
                ticket.getStadiumName(),
                ticket.getStandName(),
                ticket.getSeatNumber(),
                ticket.getGate(),
                ticket.getMatchDate(),
                ticket.getCreatedAt()
        );
    }
}