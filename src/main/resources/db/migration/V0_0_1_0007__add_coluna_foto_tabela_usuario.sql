-- Adiciona coluna para armazenar url da foto de perfil de um usuário
ALTER TABLE mubook.usuario ADD COLUMN IF NOT EXISTS foto_url VARCHAR(255);