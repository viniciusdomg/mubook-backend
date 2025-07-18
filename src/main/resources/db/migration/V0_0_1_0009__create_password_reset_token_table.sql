-- Cria a sequência para os IDs da tabela de token de reset de senha
CREATE SEQUENCE mubook.seq_password_reset_token_id
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Cria a tabela para armazenar os tokens de reset de senha
CREATE TABLE mubook.password_reset_token (
                                             id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('mubook.seq_password_reset_token_id'),
                                             token VARCHAR(255) NOT NULL UNIQUE,
                                             usuario_id BIGINT NOT NULL,
                                             expiry_date TIMESTAMP NOT NULL,
                                             CONSTRAINT fk_password_reset_token_usuario
                                                 FOREIGN KEY (usuario_id)
                                                     REFERENCES mubook.usuario (id)
                                                     ON DELETE CASCADE -- Se o usuário for deletado, o token também será.
);

-- Adiciona um índice na coluna do token para buscas rápidas
CREATE INDEX idx_password_reset_token_token ON mubook.password_reset_token(token);