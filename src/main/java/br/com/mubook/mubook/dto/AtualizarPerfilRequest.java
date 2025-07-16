package br.com.mubook.mubook.dto;

public record AtualizarPerfilRequest(Long id, String nome, String cpf, String email, String dataNascimento,
                                     String genero, String cep, String endereco, String complemento, String telefone) {
}
