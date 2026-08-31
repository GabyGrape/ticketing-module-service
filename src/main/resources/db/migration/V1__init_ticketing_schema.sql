-- Memastikan tabel tickets dibuat jika belum ada
CREATE TABLE IF NOT EXISTS tickets (
                                       id BIGSERIAL PRIMARY KEY,
                                       event_name VARCHAR(255) NOT NULL,
    ticket_code VARCHAR(255) NOT NULL UNIQUE,
    price DECIMAL(19, 2),
    status VARCHAR(50),
    stadium_name VARCHAR(255) NOT NULL,
    stand_name VARCHAR(255) NOT NULL,
    seat_number VARCHAR(255),
    gate VARCHAR(255),
    match_date TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Membuat Indeks secara aman (menggunakan kolom snake_case sesuai pemetaan DB)
CREATE INDEX IF NOT EXISTS idx_ticket_status ON tickets(status);
CREATE INDEX IF NOT EXISTS idx_ticket_event ON tickets(event_name);