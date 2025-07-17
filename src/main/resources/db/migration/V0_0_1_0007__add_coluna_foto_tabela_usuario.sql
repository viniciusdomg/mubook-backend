-- Adiciona coluna para armazenar url da foto de perfil de um usuario
ALTER TABLE mubook.usuario ADD COLUMN IF NOT EXISTS foto_url TEXT;