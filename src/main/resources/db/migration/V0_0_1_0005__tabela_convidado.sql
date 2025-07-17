-- Criação da tabela convidado dentro do schema 'mubook'
-- Esta tabela agora representa apenas a existência de um convidado como uma entidade,
-- ligada a uma pessoa.
CREATE TABLE IF NOT EXISTS mubook.convidado (
                                                id BIGSERIAL PRIMARY KEY,
                                                pessoa_id BIGINT NOT NULL,

    -- Referência à tabela pessoa no schema mubook
                                                CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_id) REFERENCES mubook.pessoa(id) ON DELETE CASCADE
    );

COMMENT ON TABLE mubook.convidado IS 'Tabela que armazena os registros de convidados, vinculados a uma pessoa.';
COMMENT ON COLUMN mubook.convidado.id IS 'Identificador único do convidado';
COMMENT ON COLUMN mubook.convidado.pessoa_id IS 'Referência à pessoa que é o convidado.';