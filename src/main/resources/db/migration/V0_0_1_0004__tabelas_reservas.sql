-- Criação da tabela reserva dentro do schema 'mubook'
CREATE TABLE IF NOT EXISTS mubook.reserva (
                                id BIGSERIAL PRIMARY KEY,
                                data_hora TIMESTAMP NOT NULL,
                                usuario_id BIGINT NOT NULL,
                                status VARCHAR(255) NOT NULL,
                                quadra_id INTEGER NOT NULL,

    -- Referência correta, especificando o schema da tabela 'usuario'
                                CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES mubook.usuario(id),

    -- Também é uma boa prática referenciar a tabela 'quadra' com seu schema
                                CONSTRAINT fk_quadra FOREIGN KEY (quadra_id) REFERENCES mubook.quadra(id)
);