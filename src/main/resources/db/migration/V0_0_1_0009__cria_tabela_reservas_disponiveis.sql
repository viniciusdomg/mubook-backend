-- Altera o nome da tabela que atualmente e reserva para historico reserva
CREATE TABLE IF NOT EXISTS mubook.reservas_disponiveis(
  id BIGSERIAL PRIMARY KEY,
  data_hora TIMESTAMP NOT NULL,
  usuario_id BIGINT NOT NULL,
  status VARCHAR(255) NOT NULL,
  quadra_id INTEGER NOT NULL,
  CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES mubook.usuario(id),
  CONSTRAINT fk_quadra FOREIGN KEY (quadra_id) REFERENCES mubook.quadra(id)
);
