package com.oksys.backend.repository;

import com.oksys.backend.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Derived Query Method: Spring Data JPA otomatis mentranslasikan nama method ini
    // menjadi query SQL: SELECT * FROM tickets WHERE ticket_code = ?
    Optional<Ticket> findByTicketCode(String ticketCode);

    // OPTIMISASI QUERY (Paging & Sorting):
    // Dengan mengembalikan `Page<Ticket>` dan menerima `Pageable`, Hibernate akan mengeksekusi
    // query SQL `LIMIT` dan `OFFSET` agar database tidak meledak mengambil seluruh row sekaligus.
    Page<Ticket> findByStatus(String status, Pageable pageable);

    // Custom JPQL Query: Menggunakan sintaks JPQL (berbasis Object Java, bukan tabel database langsung)
    // Digunakan saat butuh pencarian custom seperti klausa LIKE / pencarian teks
    @Query("SELECT t FROM Ticket t WHERE t.eventName LIKE %:eventName%")
    Page<Ticket> searchByEventName(@Param("eventName") String eventName, Pageable pageable);
}
