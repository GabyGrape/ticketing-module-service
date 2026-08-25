package com.oksys.backend.controller;

import com.oksys.backend.dto.TicketRequest;
import com.oksys.backend.dto.TicketResponse;
import com.oksys.backend.service.impl.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController: Menandai class ini sebagai Controller yang otomatis mengembalikan data berformat JSON (bukan HTML)
@RestController
@CrossOrigin(origins = "http://localhost:5173")
// @RequestMapping: Base URL path untuk seluruh endpoint di class ini
@RequestMapping("/api/v1/tickets")
public class TicketController {
    private final TicketService ticketService;

    // Constructor Injection untuk menghubungkan Controller dengan Service Layer
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // 1. CREATE TICKET
    // Endpoint: POST http://localhost:8080/api/v1/tickets
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest request) {
        // @RequestBody: Membaca payload JSON dari request body dan memetakannya ke objek TicketRequest
        TicketResponse response = ticketService.createTicket(request);
        // Mengembalikan HTTP Status 201 Created
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. GET ALL TICKETS WITH PAGINATION
    // Endpoint: GET http://localhost:8080/api/v1/tickets?page=0&size=10&sortBy=id
    @GetMapping
    public ResponseEntity<Page<TicketResponse>> getAllTickets(
            // @RequestParam: Mengambil query parameter dari URL, jika client tidak mengirimkannya maka pakai defaultValue
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        // Mengembalikan HTTP Status 200 OK beserta pagination metadata (totalPages, totalElements, content)
        return ResponseEntity.ok(ticketService.getAllTickets(page, size, sortBy));
    }

    // 3. GET TICKET BY ID
    // Endpoint: GET http://localhost:8080/api/v1/tickets/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long id) {
        // @PathVariable: Mengambil nilai variabel dari URI path segment ({id})
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    // 4. UPDATE TICKET
    // Endpoint: PUT http://localhost:8080/api/v1/tickets/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
            @PathVariable Long id,
            @RequestBody TicketRequest request
    ) {
        // Mengubah data lama dengan data baru berdasarkan ID
        return ResponseEntity.ok(ticketService.updateTicket(id, request));
    }

    // 5. DELETE TICKET
    // Endpoint: DELETE http://localhost:8080/api/v1/tickets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        // Mengembalikan HTTP Status 200 OK dengan pesan sukses
        return ResponseEntity.ok("Tiket berhasil dihapus");
    }
}
