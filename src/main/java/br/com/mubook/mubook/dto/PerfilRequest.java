package br.com.mubook.mubook.dto;

public record PerfilRequest(Long id, String foto_url, String nome, String cpf, String email,
                            String dataNascimento, String genero, String cep, String endereco,
                            String complemento, String telefone) {
}
