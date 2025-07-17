-- Cria tabela para definir os horários de funcionamento das quadras
CREATE TABLE IF NOT EXISTS mubook.horario_funcionamento(
   id BIGSERIAL PRIMARY KEY,
   dias_semana TEXT NOT NULL,
   horario_abertura TIME NOT NULL,
   horario_fechamento TIME NOT NULL,
   tipo_quadra_id INTEGER NOT NULL,
   CONSTRAINT fk_tipo_quadra FOREIGN KEY (tipo_quadra_id) REFERENCES mubook.tipo_quadra(id)
);