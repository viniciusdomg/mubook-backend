CREATE SEQUENCE IF NOT EXISTS mubook.seq_pessoa_id
    INCREMENT BY 1
    START WITH 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS mubook.seq_usuario_id
    INCREMENT BY 1
    START WITH 1
    NO CYCLE;

CREATE TABLE mubook.pessoa (
    id BIGINT PRIMARY KEY DEFAULT nextval('mubook.seq_pessoa_id'),
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255),
    data_nascimento DATE,
    genero VARCHAR(255),
    end_rua VARCHAR(255),
    end_numero VARCHAR(10),
    end_bairro VARCHAR(255),
    end_complemento VARCHAR(255),
    end_cep VARCHAR(255)
    );

CREATE TABLE mubook.usuario (
    id BIGINT PRIMARY KEY DEFAULT nextval('mubook.seq_usuario_id'),
    senha VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(13) NOT NULL,
    pessoa_id BIGINT NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES mubook.pessoa(id)
);
