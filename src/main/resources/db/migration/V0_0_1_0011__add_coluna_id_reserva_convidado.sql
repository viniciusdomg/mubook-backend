ALTER TABLE mubook.convidado
    ADD COLUMN reserva_id INTEGER;

ALTER TABLE mubook.convidado
    ADD CONSTRAINT fk_reserva_id
        FOREIGN KEY (reserva_id)
            REFERENCES mubook.historico_reservas(id);