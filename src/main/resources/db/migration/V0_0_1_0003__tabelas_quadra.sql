-- Criação da tabela tipo_quadra
CREATE TABLE tipo_quadra (
                             id SERIAL PRIMARY KEY,
                             nome VARCHAR(100) NOT NULL
);

-- Criação da tabela quadra
CREATE TABLE quadra (
                        id SERIAL PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        tipo_quadra INTEGER NOT NULL,
                        quantidade_maxima INTEGER NOT NULL,
                        foto_url VARCHAR(255),

                        CONSTRAINT fk_tipo_quadra FOREIGN KEY (tipo_quadra) REFERENCES tipo_quadra(id)
);
