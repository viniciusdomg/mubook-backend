-- Criação da tabela de ligação para o relacionamento Muitos-para-Muitos
-- entre Reserva e Convidado.
CREATE TABLE IF NOT EXISTS mubook.reserva_convidado (
                                                        reserva_id BIGINT NOT NULL,
                                                        convidado_id BIGINT NOT NULL,

    -- Chave primária composta para garantir que um convidado não seja adicionado
    -- mais de uma vez na mesma reserva.
                                                        PRIMARY KEY (reserva_id, convidado_id),

    -- Referências às tabelas originais
    CONSTRAINT fk_reserva FOREIGN KEY (reserva_id) REFERENCES mubook.reserva(id) ON DELETE CASCADE,
    CONSTRAINT fk_convidado FOREIGN KEY (convidado_id) REFERENCES mubook.convidado(id) ON DELETE CASCADE
    );

COMMENT ON TABLE mubook.reserva_convidado IS 'Tabela de associação entre reservas e seus respectivos convidados.';